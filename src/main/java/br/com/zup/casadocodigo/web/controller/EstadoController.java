package br.com.zup.casadocodigo.web.controller;

import br.com.zup.casadocodigo.data.domain.Estado;
import br.com.zup.casadocodigo.data.repository.EstadoRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.EstadoFormRequest;
import br.com.zup.casadocodigo.web.controller.dto.response.localizacao.EstadoDto;
import br.com.zup.casadocodigo.web.controller.dto.validation.localizacao.factory.AbstractFactoryLocalizacaoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/estado-pais")
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final AbstractFactoryLocalizacaoValidator factoryValidators;

    @Autowired
    public EstadoController(EstadoRepository estadoRepository,
                            AbstractFactoryLocalizacaoValidator factory){
        this.estadoRepository = estadoRepository;
        this.factoryValidators = factory;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(this.factoryValidators.proibeEstadoDuplicadoPorPais(),
                this.factoryValidators.validaReferenciasEstado());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarEstado(@RequestBody @Valid EstadoFormRequest estadoForm){
        Estado estadoEntity = this.estadoRepository.save(estadoForm.map());
        return ResponseEntity.ok(new EstadoDto(estadoEntity));
    }
}
