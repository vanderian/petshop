package sk.vanderian.petshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import sk.vanderian.petshop.entity.Product;

import java.math.BigDecimal;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    public Page<Product> findAllByPriceBetweenAndNameStartingWith(
            BigDecimal priceLow,
            BigDecimal priceHigh,
            String namePrefix,
            Pageable pageable
    );
}
