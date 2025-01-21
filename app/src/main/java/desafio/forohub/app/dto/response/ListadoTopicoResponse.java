package desafio.forohub.app.dto.response;

import desafio.forohub.app.domain.model.Topico;
import java.time.LocalDateTime;

public record ListadoTopicoResponse(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion) {
    public ListadoTopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
    }
}