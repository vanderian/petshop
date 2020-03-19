package sk.vanderian.petshop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizeFilter extends BasicAuthenticationFilter {
    private SecurityConstants securityConstants;
    private UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizeFilter(
            AuthenticationManager authManager,
            SecurityConstants securityConstants,
            UserDetailsServiceImpl userDetailsService
    ) {
        super(authManager);
        this.securityConstants = securityConstants;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(securityConstants.getHeader());

        if (header == null || !header.startsWith(securityConstants.getPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(securityConstants.getHeader());
        if (token != null) {
            // parse the token.
            String username = JWT.require(Algorithm.HMAC512(securityConstants.getJwtSecret().getBytes()))
                    .build()
                    .verify(token.replace(securityConstants.getPrefix(), ""))
                    .getSubject();

            if (username != null) {
                UserDetails user = userDetailsService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
