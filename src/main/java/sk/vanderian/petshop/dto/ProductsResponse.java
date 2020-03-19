package sk.vanderian.petshop.dto;

import lombok.Getter;
import lombok.Setter;
import sk.vanderian.petshop.entity.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductsResponse {
    private long id;
    private String name;
    private BigDecimal price;
    private List<String> categories;

    public void setCategories(List<Category> categories) {
        this.categories = categories.stream().map(Category::getName).collect(Collectors.toList());
    }
}
