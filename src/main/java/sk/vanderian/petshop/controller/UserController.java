package sk.vanderian.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sk.vanderian.petshop.repository.ProductRepository;
import sk.vanderian.petshop.repository.UserRepository;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/login")
    public void login() {
    }

    @PostMapping(path = "/register")
    public void register() {
    }

}
