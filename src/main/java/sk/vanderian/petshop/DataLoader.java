package sk.vanderian.petshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sk.vanderian.petshop.entity.AppUser;
import sk.vanderian.petshop.entity.Category;
import sk.vanderian.petshop.entity.Role;
import sk.vanderian.petshop.entity.Roles;
import sk.vanderian.petshop.repository.CategoryRepository;
import sk.vanderian.petshop.repository.RoleRepository;
import sk.vanderian.petshop.repository.UserRepository;

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
    UserRepository userRepository;

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
        Role role = roleRepository.findByName(Roles.ROLE_ADMIN).orElseThrow();
        AppUser user = new AppUser();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("admin@admin.com");
        user.setRoles(Set.of(role));

        userRepository.save(user);
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
