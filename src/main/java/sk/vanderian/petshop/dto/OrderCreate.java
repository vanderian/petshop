package sk.vanderian.petshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreate {
    public List<OrderItemCreate> orders;
}
