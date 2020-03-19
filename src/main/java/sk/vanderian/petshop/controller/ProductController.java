package sk.vanderian.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sk.vanderian.petshop.dto.ProductCreate;
import sk.vanderian.petshop.dto.ProductDetail;
import sk.vanderian.petshop.dto.ProductsFindRequest;
import sk.vanderian.petshop.dto.ProductsResponse;
import sk.vanderian.petshop.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@Validated @RequestBody ProductCreate product) {
        productService.create(product);
    }

    @GetMapping
    public Page<ProductsResponse> findAll(@Validated @ModelAttribute ProductsFindRequest req, Pageable pageable) {
        return productService.findAll(req, pageable);
    }

    @GetMapping(value = "/{id}")
    public ProductDetail findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

}
