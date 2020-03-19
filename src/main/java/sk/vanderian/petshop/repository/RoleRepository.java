package sk.vanderian.petshop.repository;

import org.springframework.data.repository.CrudRepository;
import sk.vanderian.petshop.entity.Role;
import sk.vanderian.petshop.entity.Roles;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    public Optional<Role> findByName(Roles name);
}
