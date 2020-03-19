package sk.vanderian.petshop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductsFindRequest {
    @DecimalMin("0.01")
    private BigDecimal priceLow = BigDecimal.ZERO;
    private BigDecimal priceHigh = BigDecimal.valueOf(Long.MAX_VALUE); // should use findAllPriceGreaterThen... instead if null
    @Length(max = 255)
    private String namePrefix = "";
}
