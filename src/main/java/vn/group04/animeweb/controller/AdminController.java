package vn.group04.animeweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.group04.animeweb.service.AnimeService;
import vn.group04.animeweb.service.UserService;

@RestController()
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    private UserService userService;
    private AnimeService animeService;
    @Autowired
    public AdminController(AnimeService animeService, UserService userService){
        this.animeService = animeService;
        this.userService = userService;
    }
    @DeleteMapping("/anime/delete/{id}")
    public ResponseEntity<?> deleteAnimeById(@PathVariable int id){
        ResponseEntity<?> resutl = animeService.deleteAnimeById(id);
        return resutl;
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id){
        ResponseEntity<?> resutl = userService.deleteUserById(id);
        return resutl;
    }
}
