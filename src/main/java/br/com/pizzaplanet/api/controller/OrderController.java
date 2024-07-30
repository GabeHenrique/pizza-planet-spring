package br.com.pizzaplanet.api.controller;

import br.com.pizzaplanet.config.BadgeContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

  @GetMapping("/userId")
  public String getUserId() {
    return BadgeContext.getBadge().getUserId().toString();
  }
}
