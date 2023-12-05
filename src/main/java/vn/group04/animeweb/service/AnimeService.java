package vn.group04.animeweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.group04.animeweb.entity.Anime;
import vn.group04.animeweb.repository.AnimeRepository;

import java.util.Base64;

@Service
public class AnimeService {
    private AnimeRepository animeRepository;
    @Autowired
    public  AnimeService(AnimeRepository animeRepository){
        this.animeRepository = animeRepository;
    }

    public ResponseEntity<?> deleteAnimeById(int id){
        Anime anime = animeRepository.findById(id);
        if(anime == null){
            return ResponseEntity.ok("Không tìm thấy id này!");
        }else{
            animeRepository.delete(anime);
            return ResponseEntity.ok(String.format("Xoá thành công %d !!!", anime.getId()));
        }
    }

}
