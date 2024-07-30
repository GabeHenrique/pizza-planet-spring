package br.com.pizzaplanet.filter;

import br.com.pizzaplanet.api.service.auth.UserService;
import br.com.pizzaplanet.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    if (!request.getRequestURI().contains("/auth/")) {
      var tokenJWT = recuperarToken(request);
      if (tokenJWT != null) {
        var subject = jwtUtil.getSubject(tokenJWT);
        var authentication = new UsernamePasswordAuthenticationToken(subject, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }

  private String recuperarToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
      return authorizationHeader.replace("Bearer ", "");
    }
    return null;
  }
}