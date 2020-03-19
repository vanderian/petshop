package sk.vanderian.petshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.vanderian.petshop.dto.ProductCreate;
import sk.vanderian.petshop.dto.ProductDetail;
import sk.vanderian.petshop.dto.ProductsResponse;
import sk.vanderian.petshop.entity.Product;
import sk.vanderian.petshop.repository.CategoryRepository;
import sk.vanderian.petshop.repository.GalleryItemRepository;
import sk.vanderian.petshop.repository.ProductRepository;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    GalleryItemRepository galleryItemRepository;

    @Autowired
    ModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody ProductCreate product) {
        Product entity = mapper.map(product, Product.class);
        productRepository.save(entity);
    }

    @GetMapping
    public Page<ProductsResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(product -> mapper.map(product, ProductsResponse.class));
    }

    @GetMapping(value = "/{id}")
    public ProductDetail findById(@PathVariable("id") Long id) {
        return productRepository.findById(id).map(product -> mapper.map(product, ProductDetail.class)).orElseThrow();
    }

}
