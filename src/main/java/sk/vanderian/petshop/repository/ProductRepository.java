package sk.vanderian.petshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.vanderian.petshop.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
