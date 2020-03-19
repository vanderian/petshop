package sk.vanderian.petshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @NotNull
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderItem> orders;

    @OneToOne()
    @NotNull
    private AppUser user;

}
