package desafio.forohub.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import desafio.forohub.app.domain.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
}