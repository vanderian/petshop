package sk.vanderian.petshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import sk.vanderian.petshop.entity.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CategoryCreate {
    @NotNull
    @NotEmpty
    @Schema(example = "bone toy")
    private String name;
}
