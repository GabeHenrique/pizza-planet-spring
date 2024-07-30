package br.com.pizzaplanet.api.controller.auth;

import br.com.pizzaplanet.api.repository.model.User;
import br.com.pizzaplanet.api.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping(path = "/token", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> token(@Valid User login) {
    var accessToken = authService.createAuthenticationToken(login);
    return ResponseEntity.ok(accessToken);
  }

  @PostMapping("/user")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createUser(@RequestBody User user) {
    return authService.registerUser(user);
  }
}
