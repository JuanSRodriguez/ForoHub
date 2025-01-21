package desafio.forohub.app.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegistrarTopicoRequest(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,
        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje
) {
}