package vn.group04.animeweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.group04.animeweb.entity.Anime;

import java.util.List;

@RepositoryRestResource(path="anime")
public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    public Anime findByName(String name);
    public Anime findById(int id);
    public Page<Anime> findAllByNameContains(String name, Pageable pageable);
    public Page<Anime> findAllByCategoryList_categoryName(String theLoai, Pageable pageable);

}
