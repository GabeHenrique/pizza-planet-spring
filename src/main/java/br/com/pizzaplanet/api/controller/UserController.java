package br.com.pizzaplanet.api.controller;

import br.com.pizzaplanet.api.controller.dto.RegisterUserDto;
import br.com.pizzaplanet.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createUser(@RequestBody RegisterUserDto userDto) {
    return userService.registerUser(userDto);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> getLoggedUser() {
    return userService.getDetailsFromLoggedUser();
  }
}
