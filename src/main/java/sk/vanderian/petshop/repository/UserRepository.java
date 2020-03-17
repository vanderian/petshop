package sk.vanderian.petshop.repository;

import org.springframework.data.repository.CrudRepository;
import sk.vanderian.petshop.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
