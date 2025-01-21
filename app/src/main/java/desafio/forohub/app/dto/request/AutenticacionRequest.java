package desafio.forohub.app.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AutenticacionRequest(
        @NotBlank(message = "El login no puede estar vacío")
        String login,
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password
) {
}