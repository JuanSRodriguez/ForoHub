package desafio.forohub.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.forohub.app.dto.request.ActualizarTopicoRequest;
import desafio.forohub.app.dto.request.RegistrarTopicoRequest;
import desafio.forohub.app.dto.response.ListadoTopicoResponse;
import desafio.forohub.app.dto.response.TopicoResponse;
import desafio.forohub.app.service.TopicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<ListadoTopicoResponse>> listar(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion){
       return ResponseEntity.ok(topicoService.listar(paginacion));
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody @Valid RegistrarTopicoRequest datos){
        topicoService.registrar(datos);
        return ResponseEntity.ok("topico creado");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody @Valid ActualizarTopicoRequest datos){
        topicoService.actualizar(id, datos);
        return ResponseEntity.ok("topico actualizado");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
       topicoService.eliminar(id);
        return ResponseEntity.ok("topico eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> getById(@PathVariable Long id){
       return ResponseEntity.ok(topicoService.getById(id));
    }
}