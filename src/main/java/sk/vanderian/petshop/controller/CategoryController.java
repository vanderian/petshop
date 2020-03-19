package sk.vanderian.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sk.vanderian.petshop.dto.CategoryCreate;
import sk.vanderian.petshop.dto.CategoryResponse;
import sk.vanderian.petshop.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Validated @RequestBody CategoryCreate categoryCreate) {
        categoryService.create(categoryCreate);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

}
