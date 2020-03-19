package sk.vanderian.petshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.repository.CategoryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader implements ApplicationRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void run(ApplicationArguments args) {
        seedCategories();
    }

    private void seedCategories() {
        if (categoryRepository.count() != 0) return;

        List<Category> categories = Stream.of("dogs", "cats", "other")
                .map(name -> {
                    Category category = new Category();
                    category.setName(name);
                    return category;
                })
                .collect(Collectors.toList());

        categoryRepository.saveAll(categories);
    }
}
