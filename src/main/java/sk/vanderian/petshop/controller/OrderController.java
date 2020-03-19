package sk.vanderian.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sk.vanderian.petshop.dto.OrderCreate;
import sk.vanderian.petshop.dto.OrderResponse;
import sk.vanderian.petshop.service.OrderService;

import java.security.Principal;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Validated @RequestBody OrderCreate orderCreate, Principal principal) {
        orderService.create(orderCreate, principal.getName());
    }

    @GetMapping(path = "/me")
    @PreAuthorize("hasRole('USER')")
    public Page<OrderResponse> findAllPerUser(Pageable pageable, Principal principal) {
        return orderService.findAllPerUser(pageable, principal.getName());
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Page<OrderResponse> findAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

}
