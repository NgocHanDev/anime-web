package vn.group04.animeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.group04.animeweb.entity.Episode;
@RepositoryRestResource(path = "tap")
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    public int countAllByAnime_Id(int id);
}
