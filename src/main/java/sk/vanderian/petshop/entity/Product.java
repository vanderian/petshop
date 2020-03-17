package sk.vanderian.petshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String name;
    private final BigDecimal price;
    private final String description;

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<Category> categories;

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<GalleryItem> gallery;
}
