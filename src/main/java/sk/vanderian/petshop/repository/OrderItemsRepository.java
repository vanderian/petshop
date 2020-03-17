package sk.vanderian.petshop.repository;

import org.springframework.data.repository.CrudRepository;
import sk.vanderian.petshop.entity.OrderItem;

public interface OrderItemsRepository extends CrudRepository<OrderItem, Long> {

}
