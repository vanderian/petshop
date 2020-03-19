package sk.vanderian.petshop.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SecurityConstants {
    public static final String SIGN_UP_URL = "/users/register";

    @Value("${petshop.app.jwtSecret}")
    private String jwtSecret;

    @Value("${petshop.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private String prefix = "Bearer ";
    private String header = "Authorization";
}
