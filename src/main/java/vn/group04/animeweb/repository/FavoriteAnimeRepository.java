package vn.group04.animeweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.group04.animeweb.entity.FavoriteAnime;

import java.util.Optional;

@RepositoryRestResource(path = "danh-sach-yeu-thich")
public interface FavoriteAnimeRepository extends JpaRepository<FavoriteAnime, Integer> {
    public FavoriteAnime findByAnime_IdAndUser_Id(int animeId, int userId);
    public Page<FavoriteAnime> findAllByUser_Id(int userId, Pageable pageable);
    public boolean existsByAnime_IdAndUser_Id(int animeId, int userId);
    public int countAllByAnime_IdAndUser_Id(int animeId, int userId);
}
