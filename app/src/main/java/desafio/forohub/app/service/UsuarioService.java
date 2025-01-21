package desafio.forohub.app.service;

import java.util.NoSuchElementException;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import desafio.forohub.app.domain.model.Usuario;
import desafio.forohub.app.domain.repository.UsuarioRepository;
import desafio.forohub.app.dto.request.AutenticacionRequest;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username).orElseThrow(() -> new NoSuchElementException("No existe un usuario con ese nombre"));
    }

    public void registrar(AutenticacionRequest data) {
        Usuario usuario = new Usuario(data.login(), passwordEncoder.encode(data.password()));
        usuarioRepository.save(usuario);
    }
}