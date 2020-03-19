package sk.vanderian.petshop.repository;

import org.springframework.data.repository.CrudRepository;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.entity.GalleryItem;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    public Optional<Category> findByName(String name);
}
