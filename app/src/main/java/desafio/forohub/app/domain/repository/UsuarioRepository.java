package desafio.forohub.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import desafio.forohub.app.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByLogin(String login);
}