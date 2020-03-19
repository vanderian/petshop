package sk.vanderian.petshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sk.vanderian.petshop.dto.UserDto;
import sk.vanderian.petshop.entity.AppUser;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.entity.Role;
import sk.vanderian.petshop.entity.Roles;
import sk.vanderian.petshop.repository.CategoryRepository;
import sk.vanderian.petshop.repository.RoleRepository;
import sk.vanderian.petshop.repository.UserRepository;
import sk.vanderian.petshop.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    public void run(ApplicationArguments args) {
        if (categoryRepository.count() != 0) return;

        seedCategories();
        seedRoles();
        seedAdmin();
    }

    private void seedRoles() {
        List<Role> roles = Arrays.stream(Roles.values()).map(r -> new Role(0, r)).collect(Collectors.toList());
        roleRepository.saveAll(roles);
    }

    private void seedAdmin() {
        UserDto user = new UserDto();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("admin@admin.com");
        userService.create(user, Roles.ROLE_ADMIN);
    }

    private void seedCategories() {
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
