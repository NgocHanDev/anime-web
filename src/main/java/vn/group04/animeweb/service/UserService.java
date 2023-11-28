package vn.group04.animeweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.group04.animeweb.entity.Role;
import vn.group04.animeweb.entity.User;
import vn.group04.animeweb.repository.UserRepository;
import vn.group04.animeweb.response.MyResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User nguoiDung = userRepository.findByUserName(username);
        if(nguoiDung == null){
            throw new UsernameNotFoundException("Người Dùng Không Tồn Tại!");
        }

        return new org.springframework.security.core.userdetails.User(nguoiDung.getUserName(), nguoiDung.getPassword(), rolesToAuthorities(nguoiDung.getRoleList()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(List<Role> roleList) {
        return  roleList.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    public ResponseEntity<?> deleteUserById(int id) {
        User user = userRepository.findById(id);
        MyResponse response = new MyResponse();
        if(user == null){
            response.setMessage("Người dùng không tồn tại!");
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }else{
            userRepository.delete(user);
            response.setMessage("Xoá Thành công người dùng mang id="+id);
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return ResponseEntity.ok(response);
    }




}
