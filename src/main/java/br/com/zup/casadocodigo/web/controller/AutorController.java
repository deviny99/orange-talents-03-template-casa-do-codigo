package br.com.zup.casadocodigo.web.controller;

import br.com.zup.casadocodigo.data.domain.Autor;
import br.com.zup.casadocodigo.data.repository.AutorRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.AutorFormRequest;
import br.com.zup.casadocodigo.web.controller.dto.response.AutorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarAutor(@RequestBody @Valid AutorFormRequest autorFormRequest){
        Autor autorEntity = this.autorRepository.save(new Autor(autorFormRequest.getNome() ,autorFormRequest.getEmail(),autorFormRequest.getDescricao()));
        return ResponseEntity.ok(new AutorDto(autorEntity));
    }
}