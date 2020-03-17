package sk.vanderian.petshop.repository;

import org.springframework.data.repository.CrudRepository;
import sk.vanderian.petshop.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
