package br.com.pizzaplanet.api.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginDto(@NotEmpty String username, @NotEmpty String password) {
}
