package sk.vanderian.petshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import sk.vanderian.petshop.entity.Category;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductCreate {
    @NotNull
    @NotEmpty
    @Schema(example = "bone toy")
    @Length(max = 255)
    private String name;

    @NotNull
    @Min(0)
    @Schema(example = "42")
    @DecimalMin("0.01")
    private BigDecimal price;

    @NotNull
    @Schema(example = "wow such toy much bone")
    @Length(max = 255)
    private String description;

//    @Schema(example = "dog")
    private List<Long> categoryIds;
//    @Schema(example = "https://barkpost-assets.s3.amazonaws.com/wp-content/uploads/2013/11/plainDoge.jpg")
    private List<String> galleryUrls;
}
