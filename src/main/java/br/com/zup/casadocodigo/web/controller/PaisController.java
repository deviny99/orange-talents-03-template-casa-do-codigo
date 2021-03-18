package br.com.zup.casadocodigo.web.controller;

import br.com.zup.casadocodigo.data.domain.Pais;
import br.com.zup.casadocodigo.data.repository.PaisRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.PaisFormRequest;
import br.com.zup.casadocodigo.web.controller.dto.response.localizacao.PaisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/pais")
public class PaisController {

    private final PaisRepository paisRepository;

    @Autowired
    public PaisController(PaisRepository paisRepository){
        this.paisRepository = paisRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarPais(@RequestBody @Valid PaisFormRequest paisFormRequest){

        Pais paisEntity = this.paisRepository.save(new Pais(paisFormRequest.getNome()));
        return ResponseEntity.ok(new PaisDto(paisEntity));
    }

}
