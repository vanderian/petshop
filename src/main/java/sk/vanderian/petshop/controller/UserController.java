package sk.vanderian.petshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sk.vanderian.petshop.dto.UserRegister;
import sk.vanderian.petshop.entity.AppUser;
import sk.vanderian.petshop.entity.Role;
import sk.vanderian.petshop.entity.Roles;
import sk.vanderian.petshop.repository.RoleRepository;
import sk.vanderian.petshop.repository.UserRepository;
import sk.vanderian.petshop.security.SecurityConstants;

import java.util.Set;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper mapper;

    @PostMapping(path = SecurityConstants.SIGN_UP_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Validated @RequestBody UserRegister userRegister) {
        Role role = roleRepository.findByName(Roles.ROLE_USER).orElseThrow();
        AppUser appUser = mapper.map(userRegister, AppUser.class);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(Set.of(role));
        userRepository.save(appUser);
    }

}
