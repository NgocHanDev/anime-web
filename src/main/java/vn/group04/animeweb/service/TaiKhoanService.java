package vn.group04.animeweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.group04.animeweb.entity.FavoriteAnime;
import vn.group04.animeweb.entity.User;
import vn.group04.animeweb.repository.FavoriteAnimeRepository;
import vn.group04.animeweb.repository.UserRepository;
import vn.group04.animeweb.request.UserChangePassword;
import vn.group04.animeweb.response.MyResponse;
import vn.group04.animeweb.security.login.RequestLogin;
import vn.group04.animeweb.security.login.ResponseLogin;

import java.util.Date;
import java.util.Random;

@Service
public class TaiKhoanService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final FavoriteAnimeRepository favoriteAnimeRepository;


    @Autowired
    public TaiKhoanService(EmailService emailService, UserRepository userRepository,BCryptPasswordEncoder passwordEncoder, JwtService jwtService, UserService userService, AuthenticationManager authenticationManager,FavoriteAnimeRepository favoriteAnimeRepository){
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.favoriteAnimeRepository = favoriteAnimeRepository;

    }
    public ResponseEntity<?> dangNhap(RequestLogin request) {
        if(request != null){
            try {
                Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
                if (auth.isAuthenticated()) {
                    String token = jwtService.generateToken(userService.loadUserByUsername(request.getUserName()));

                    ResponseLogin response = new ResponseLogin();
                    response.setToken(token);
                    response.setDate(new Date(System.currentTimeMillis()));
                    return ResponseEntity.ok(response);
                }
            }catch (AuthenticationException e){
                return ResponseEntity.badRequest().body("Tài khoản hoặc mật khẩu không chính xác");
            }
        }
        return ResponseEntity.badRequest().body("Đăng Nhập không thành công!");
    }

    public ResponseEntity<?> dangKy(User user) {
        MyResponse myResponse = new MyResponse();
        //kiểm tra tên đăng nhập
        if (userRepository.existsByUserName(user.getUserName())) {
           myResponse.setMessage("Tên đăng nhập đã tồn tại!");
           myResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.ok(myResponse);
        }
        //kiểm tra email
        if (userRepository.existsByEmail(user.getEmail())) {
            myResponse.setMessage("Email này đã được sử dụng!");
            myResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.ok(myResponse);
        }

        //mã hoá mật khẩu
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);

        //lưu người dùng vào hệ thống
        userRepository.save(user);
        myResponse.setMessage("Đăng ký thành công!");
        myResponse.setStatus(HttpStatus.OK.value());
        myResponse.setData(user);
        return ResponseEntity.ok(myResponse);
    }

    public ResponseEntity<?> deleteFavoriteAnimeById(int animeId, int userId) {
        FavoriteAnime favoriteAnime = favoriteAnimeRepository.findByAnime_IdAndUser_Id(animeId, userId);
        MyResponse myResponse = new MyResponse();
        if(favoriteAnime == null){
            myResponse.setStatus(HttpStatus.NOT_FOUND.value());
            myResponse.setMessage("Anime yêu thích không tồn tại!");
        }else{
            favoriteAnimeRepository.deleteById(favoriteAnime.getId());
            myResponse.setStatus(HttpStatus.OK.value());
            myResponse.setMessage("Xoá thành công id:"+favoriteAnime.getId());
        }
        return ResponseEntity.ok(myResponse );
    }

    public ResponseEntity<?> resetPassword(String email) {
        //tìm kiếm người dùng có tồn tại trong  data
        User user = userRepository.findByEmail(email);
        MyResponse myResponse = new MyResponse();
        if(user == null){
            myResponse.setMessage("Người dùng không tồn tại trong hệ thống!");
            myResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }else {
            //tạo password mới cho người dùng
            String newPassword = new Random().nextInt((999999 - 100000) + 1) + 100000 +"";
            //mã hoá và lưu
            String encryptPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encryptPassword);
            userRepository.save(user);

            //tiến hành gửi email reset
            sendEmailResetPassword(email, newPassword);
            myResponse.setMessage("Mật khẩu mới đã được gửi vào email của bạn! Vui lòng kiểm tra email!");
            myResponse.setStatus(HttpStatus.OK.value());
        }
        return ResponseEntity.ok(myResponse);
    }

    private void sendEmailResetPassword(String email, String newPassword) {
        String subject = "Lấy Lại Mật Khẩu Tại Group4-Wibu";
        String text = String.format(
                """
                <style>
                    .title {
                        display: flex;
                        justify-content: center
                    }
                                
                    h2 {
                        background-color: cyan;
                        width: max-content;
                    }
                                
                    strong {
                        text-decoration: underline
                    }
                </style>
                <div class="container">
                    <div class="title">
                        <h2>Email Qu&ecirc;n Mật Khẩu</h2>
                    </div>
                    <div class="content">
                        Mật Khẩu Mới Của Bạn Là: <strong>%s</strong>
                    </div>
                </div>
                """, newPassword);
        emailService.sendMessage("nguyenngochandeveloper@gmail.com", email, subject, text);
    }

    public ResponseEntity<?> changePassword(UserChangePassword userChangePassword) {
        //tìm trong database
        User user = userRepository.findByUserName(userChangePassword.getUserName());

        MyResponse myResponse = new MyResponse();
        if(user == null){
            myResponse.setStatus(HttpStatus.NOT_FOUND.value());
            myResponse.setMessage("Tài khoản không tồn tại!");
            return ResponseEntity.badRequest().body(myResponse);
        }
        String currentPassword = userChangePassword.getCurrentPassword();//mật khẩu hiện tại mà người dùng nhập vào
        if(!passwordEncoder.matches(currentPassword, user.getPassword())){//2 mk kkhoong trùng khớp
            myResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            myResponse.setMessage("Mật Khẩu Cũ Không Trùng Khớp!");
            return ResponseEntity.badRequest().body(myResponse);
        }
        //tất cả đã trùng khớp
        String newPassword = userChangePassword.getNewPassword();
        String encodePassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodePassword);
        userRepository.save(user);

        myResponse.setStatus(HttpStatus.OK.value());
        myResponse.setMessage("Thay đổi mật khẩu thành công!");
        return ResponseEntity.ok(myResponse);
    }
}
