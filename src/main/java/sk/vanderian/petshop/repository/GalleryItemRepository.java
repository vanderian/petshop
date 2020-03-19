package sk.vanderian.petshop.repository;

import org.springframework.data.repository.CrudRepository;
import sk.vanderian.petshop.entity.GalleryItem;

public interface GalleryItemRepository extends CrudRepository<GalleryItem, Long> {
}
