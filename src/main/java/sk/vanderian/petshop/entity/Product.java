package sk.vanderian.petshop.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @NotNull
    @NotEmpty
    @Length(max = 255)
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @Length(max = 255)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> categories;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<GalleryItem> gallery;

    public void setCategoryIds(List<Long> ids) {
        this.setCategories(ids.stream().map(id -> {
            Category category = new Category();
            category.setId(id);
            return category;
        }).collect(Collectors.toSet()));
    }

    public void setGalleryUrls(List<String> urls) {
        this.setGallery(urls.stream().map(url -> new GalleryItem(0, url)).collect(Collectors.toSet()));
    }
}
