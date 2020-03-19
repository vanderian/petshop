package com.vanderian.petshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.vanderian.petshop.dto.CategoryResponse;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.repository.CategoryRepository;
import sk.vanderian.petshop.service.CategoryService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

// sample unit test
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void givenCategories_whenGetCategories_thenReturnJsonArray()
            throws Exception {

        CategoryResponse cat = new CategoryResponse(10L, "cat");
        List<CategoryResponse> cats = Collections.singletonList(cat);
        Category entity = new Category();
        entity.setName(cat.getName());
        entity.setId(cat.getId());
        when(categoryRepository.findAll()).thenReturn(Collections.singleton(entity));

        List<CategoryResponse> responses = categoryService.findAll();
        Assertions.assertArrayEquals(responses.toArray(), cats.toArray());
    }
}
