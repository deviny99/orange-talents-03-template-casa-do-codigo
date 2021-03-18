package br.com.zup.casadocodigo.web.controller;

import br.com.zup.casadocodigo.data.domain.Cliente;
import br.com.zup.casadocodigo.data.repository.ClienteRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.ClienteFormRequest;
import br.com.zup.casadocodigo.web.controller.dto.validation.cliente.factory.AbstractFactoryClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    //2
    private final ClienteRepository clienteRepository;
    private final AbstractFactoryClienteValidator clienteFactoryValidator;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository,
                             AbstractFactoryClienteValidator factoryCliente){
        this.clienteRepository = clienteRepository;
        this.clienteFactoryValidator = factoryCliente;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(this.clienteFactoryValidator.proibeDocumentoDuplicado(),
                this.clienteFactoryValidator.validaReferenciasCliente(),
                this.clienteFactoryValidator.verificaEstadoPertenceAoPaisSelecionado(),
                this.clienteFactoryValidator.validaObrigatoriedadeEstado());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid ClienteFormRequest clienteForm, UriComponentsBuilder uriBuilder){
        //1
        Cliente clienteEntity = this.clienteRepository.save(clienteForm.map());
        URI uri = uriBuilder.path("/casacodigo/cliente/{id}").buildAndExpand(clienteEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteEntity.getId());
    }
}
