package sk.vanderian.petshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class OrderResponse {
    private long id;
    private BigDecimal price;
    private List<OrderItemResponse> orders;
    private LocalDateTime createdAt;
}
