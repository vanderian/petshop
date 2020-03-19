package sk.vanderian.petshop.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper mapper;

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
