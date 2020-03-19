package sk.vanderian.petshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sk.vanderian.petshop.dto.UserDto;
import sk.vanderian.petshop.entity.AppUser;
import sk.vanderian.petshop.entity.Role;
import sk.vanderian.petshop.entity.Roles;
import sk.vanderian.petshop.repository.RoleRepository;
import sk.vanderian.petshop.repository.UserRepository;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper mapper;

    public void createUser(UserDto userDto) {
        create(userDto, Roles.ROLE_USER);
    }

    public void create(UserDto userDto, Roles roles) {
        Role role = roleRepository.findByName(roles);
        AppUser appUser = mapper.map(userDto, AppUser.class);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(Set.of(role));
        userRepository.save(appUser);
    }

}
