package desafio.forohub.app.dto.response;

import desafio.forohub.app.domain.model.Topico;
import java.time.LocalDateTime;

public record TopicoResponse(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion) {
    public TopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
    }
}