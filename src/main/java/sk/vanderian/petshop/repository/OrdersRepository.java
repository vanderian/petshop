package sk.vanderian.petshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import sk.vanderian.petshop.App;
import sk.vanderian.petshop.entity.AppUser;
import sk.vanderian.petshop.entity.Order;

public interface OrdersRepository extends PagingAndSortingRepository<Order, Long> {
    public Page<Order> findAllByUserUsername(String username, Pageable pageable);
}
