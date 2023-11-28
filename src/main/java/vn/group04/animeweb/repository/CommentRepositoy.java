package vn.group04.animeweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.group04.animeweb.entity.Comment;
@RepositoryRestResource(path = "binh-luan")
public interface CommentRepositoy extends JpaRepository<Comment, Integer> {
    public int countAllByAnime_Id(int id);
    public int countAllByUser_Id(int id);
    public Page<Comment> findAllByAnime_Id(int id, Pageable pageable);
}
