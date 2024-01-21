package vn.group04.animeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.group04.animeweb.entity.Role;
@RepositoryRestResource(path = "quyen")

public interface RoleRepository extends JpaRepository< Role, Integer> {
    public Role findByRoleName(String roleName);
}
