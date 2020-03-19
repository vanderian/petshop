package sk.vanderian.petshop.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sk.vanderian.petshop.dto.ProductCreate;
import sk.vanderian.petshop.dto.ProductDetail;
import sk.vanderian.petshop.dto.ProductsFindRequest;
import sk.vanderian.petshop.dto.ProductsResponse;
import sk.vanderian.petshop.entity.Product;
import sk.vanderian.petshop.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public void create(ProductCreate product) {
        Product entity = mapper.map(product, Product.class);
        productRepository.save(entity);
    }

    public Page<ProductsResponse> findAll(ProductsFindRequest req, Pageable pageable) {
        return productRepository.findAllByPriceBetweenAndNameStartingWith(
                req.getPriceLow(),
                req.getPriceHigh(),
                req.getNamePrefix(),
                pageable
        ).map(product -> mapper.map(product, ProductsResponse.class));
    }

    public ProductDetail findById(Long id) {
        return productRepository.findById(id).map(product -> mapper.map(product, ProductDetail.class)).orElseThrow();
    }

}
