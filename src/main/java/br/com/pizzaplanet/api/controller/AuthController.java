package br.com.pizzaplanet.api.controller;

import br.com.pizzaplanet.api.controller.dto.LoginDto;
import br.com.pizzaplanet.api.controller.dto.RegisterUserDto;
import br.com.pizzaplanet.api.repository.model.User;
import br.com.pizzaplanet.api.service.AuthService;
import br.com.pizzaplanet.config.BadgeContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping(path = "/token", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> token(@Valid LoginDto loginDto) {
    var accessToken = authService.createAuthenticationToken(loginDto);
    System.out.println(BadgeContext.getBadge().getUserId());
    return ResponseEntity.ok(accessToken);
  }
}
