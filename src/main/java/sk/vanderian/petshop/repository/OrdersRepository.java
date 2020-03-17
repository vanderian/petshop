package sk.vanderian.petshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.vanderian.petshop.entity.Order;

public interface OrdersRepository extends PagingAndSortingRepository<Order, Long> {

}
