package sk.vanderian.petshop.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sk.vanderian.petshop.dto.CategoryCreate;
import sk.vanderian.petshop.dto.CategoryResponse;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void create(CategoryCreate categoryCreate) {
        Category entity = modelMapper.map(categoryCreate, Category.class);
        categoryRepository.save(entity);
    }

    public List<CategoryResponse> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(category -> new CategoryResponse(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

}
