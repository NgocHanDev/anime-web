package vn.group04.animeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.group04.animeweb.entity.User;
@RepositoryRestResource(path = "nguoi-dung")
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserName(String userName);
    public  User findById(int id);
    public User findByEmail(String email);
    public boolean existsByUserName(String userName);
    public boolean existsByEmail(String email);
}
