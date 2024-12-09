package br.com.user.productsore.usersApi.conf.security;

import br.com.user.productsore.usersApi.repository.UserRepository;
import br.com.user.productsore.usersApi.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    final TokenService tokenService;

    final UserRepository  userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = recoverToken(request);
        if(token != null) {
            var username = tokenService.validateToken(token);
            UserDetails user = userRepository.findByUsername(username);

            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if(authHeader != null) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
