package sk.vanderian.petshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sk.vanderian.petshop.dto.OrderCreate;
import sk.vanderian.petshop.dto.OrderResponse;
import sk.vanderian.petshop.entity.AppUser;
import sk.vanderian.petshop.entity.Order;
import sk.vanderian.petshop.entity.OrderItem;
import sk.vanderian.petshop.entity.Product;
import sk.vanderian.petshop.repository.OrdersRepository;
import sk.vanderian.petshop.repository.ProductRepository;
import sk.vanderian.petshop.repository.UserRepository;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    public void create(OrderCreate orderCreate, String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow();

        Set<OrderItem> orders = orderCreate.getOrders().stream().map(o -> {
            Product product = productRepository.findById(o.getProductId()).orElseThrow();
            OrderItem orderItem = mapper.map(o, OrderItem.class);
            orderItem.setId(0);
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getCount())));
            return orderItem;
        }).collect(Collectors.toSet());

        BigDecimal price = orders.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order();
        order.setOrders(orders);
        order.setPrice(price);
        order.setUser(user);

        ordersRepository.save(order);
    }

    public Page<OrderResponse> findAllPerUser(Pageable pageable, String username) {
        return ordersRepository.findAllByUserUsername(username, pageable).map(o -> mapper.map(o, OrderResponse.class));
    }

    public Page<OrderResponse> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable).map(o -> mapper.map(o, OrderResponse.class));
    }

}
