package sk.vanderian.petshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sk.vanderian.petshop.dto.CategoryCreate;
import sk.vanderian.petshop.dto.CategoryResponse;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Validated @RequestBody CategoryCreate categoryCreate) {
        Category entity = modelMapper.map(categoryCreate, Category.class);
        categoryRepository.save(entity);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryResponse> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(category -> new CategoryResponse(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

}
