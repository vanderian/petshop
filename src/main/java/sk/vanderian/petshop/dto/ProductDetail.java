package sk.vanderian.petshop.dto;

import lombok.Getter;
import lombok.Setter;
import sk.vanderian.petshop.entity.GalleryItem;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDetail {
    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    private List<CategoryResponse> categories;
    private List<GalleryItem> galleryItems;
}
