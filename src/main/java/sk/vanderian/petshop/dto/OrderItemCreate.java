package sk.vanderian.petshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreate {

    @NotNull
    @NotEmpty
    @Schema(example = "1")
    @Min(1)
    private long productId;

    @NotNull
    @NotEmpty
    @Schema(example = "5")
    @Min(1)
    private int count;
}
