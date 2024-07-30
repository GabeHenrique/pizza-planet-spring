package br.com.pizzaplanet.api.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserDto(@NotEmpty String username, @NotEmpty String password) {
}
