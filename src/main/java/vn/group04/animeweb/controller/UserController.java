package vn.group04.animeweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.group04.animeweb.entity.User;
import vn.group04.animeweb.request.UserChangePassword;
import vn.group04.animeweb.security.login.RequestLogin;
import vn.group04.animeweb.service.JwtService;
import vn.group04.animeweb.service.TaiKhoanService;
import vn.group04.animeweb.service.UserService;

@RestController()
@RequestMapping("/user")
public class UserController {
    private TaiKhoanService taiKhoanService;
    @Autowired
    public UserController(TaiKhoanService taiKhoanService){
        this.taiKhoanService = taiKhoanService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> dangNhap(@RequestBody RequestLogin auth){
        ResponseEntity<?> response = taiKhoanService.dangNhap(auth);
        return response;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> dangKy(@RequestBody User user){
        ResponseEntity<?> response = taiKhoanService.dangKy(user);
        return response;
    }
    @DeleteMapping("/danh-sach-yeu-thich")
    public ResponseEntity<?> deleteFavoriteAnimeById(@RequestParam int animeId, @RequestParam int userId){
        ResponseEntity<?> response = taiKhoanService.deleteFavoriteAnimeById(animeId, userId);
        return response;
    }

    //phương thức đổi mật khẩu
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody UserChangePassword userChangePassword){
        ResponseEntity<?> response = taiKhoanService.changePassword(userChangePassword);
        return response;
    }
    //phương thức reset mật khẩu
    @GetMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email){
        ResponseEntity<?> response = taiKhoanService.resetPassword(email);
        return response;
    }
}
