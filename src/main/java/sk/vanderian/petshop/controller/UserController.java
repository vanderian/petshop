package sk.vanderian.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sk.vanderian.petshop.dto.UserDto;
import sk.vanderian.petshop.security.SecurityConstants;
import sk.vanderian.petshop.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = SecurityConstants.SIGN_UP_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Validated @RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }
}
