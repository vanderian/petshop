package sk.vanderian.petshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final BigDecimal price;
    private final LocalDateTime time;

    @OneToMany
    private final Set<OrderItem> orders;
}
