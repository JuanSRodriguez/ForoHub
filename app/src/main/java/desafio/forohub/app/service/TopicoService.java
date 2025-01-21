package desafio.forohub.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import desafio.forohub.app.domain.model.Topico;
import desafio.forohub.app.domain.repository.TopicoRepository;
import desafio.forohub.app.dto.request.ActualizarTopicoRequest;
import desafio.forohub.app.dto.request.RegistrarTopicoRequest;
import desafio.forohub.app.dto.response.ListadoTopicoResponse;
import desafio.forohub.app.dto.response.TopicoResponse;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Page<ListadoTopicoResponse> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(ListadoTopicoResponse::new);
    }

    public void registrar(RegistrarTopicoRequest datos) {
        Topico topico = new Topico(datos.titulo(), datos.mensaje());
        topicoRepository.save(topico);
    }

    public void actualizar(Long id, ActualizarTopicoRequest datos) {
        Topico topico = topicoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        topico.setMensaje(datos.mensaje());
        topico.setTitulo(datos.titulo());
        topicoRepository.save(topico);
    }

    public void eliminar(Long id) {
        topicoRepository.deleteById(id);
    }

    public TopicoResponse getById(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new TopicoResponse(topico);
    }
}