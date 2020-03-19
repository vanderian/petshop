package sk.vanderian.petshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponse {
    private BigDecimal price;
    private long productId;
    private long count;
}
