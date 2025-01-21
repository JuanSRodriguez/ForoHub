package desafio.forohub.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.forohub.app.dto.request.AutenticacionRequest;
import desafio.forohub.app.dto.response.AutenticacionResponse;
import desafio.forohub.app.security.filter.JwtService;
import desafio.forohub.app.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AutenticacionResponse> login(@RequestBody @Valid AutenticacionRequest data) {
        Authentication auth =  new UsernamePasswordAuthenticationToken(data.login(), data.password());
        authenticationManager.authenticate(auth);
        String token = jwtService.generateToken(data.login());
        return ResponseEntity.ok(new AutenticacionResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AutenticacionRequest data) {
        usuarioService.registrar(data);
        return ResponseEntity.ok("Usuario creado");
    }
}