package desafio.forohub.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalEceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerError404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handlerError400(MethodArgumentNotValidException ex){
        var errores = ex.getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handlerError400NoElement(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay registros con ese ID");
    }
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<String> handlerError401(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contrasena incorrecta");
    }
}