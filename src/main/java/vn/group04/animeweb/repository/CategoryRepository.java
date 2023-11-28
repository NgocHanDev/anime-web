package vn.group04.animeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.group04.animeweb.entity.Category;
@RepositoryRestResource(path = "the-loai")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
