package com.example.guzip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO (
    @NotBlank @Size(min = 4, max = 15) String username,
    @NotBlank @Size(min = 8, max = 15) String password
) {
}
