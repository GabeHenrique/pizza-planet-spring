package br.com.pizzaplanet.api.service;

import br.com.pizzaplanet.api.controller.dto.LoginDto;
import br.com.pizzaplanet.api.repository.model.User;
import br.com.pizzaplanet.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public String createAuthenticationToken(LoginDto loginDto) {
    var token = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
    var authentication = authenticationManager.authenticate(token);
    var principal = (User) authentication.getPrincipal();
    return jwtUtil.generateToken(principal);
  }
}
