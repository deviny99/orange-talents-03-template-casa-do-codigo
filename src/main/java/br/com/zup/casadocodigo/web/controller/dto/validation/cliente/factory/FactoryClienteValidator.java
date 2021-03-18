package br.com.zup.casadocodigo.web.controller.dto.validation.cliente.factory;

import br.com.zup.casadocodigo.data.repository.ClienteRepository;
import br.com.zup.casadocodigo.data.repository.EstadoRepository;
import br.com.zup.casadocodigo.data.repository.PaisRepository;
import br.com.zup.casadocodigo.web.controller.dto.validation.cliente.ProibeDocumentoDuplicado;
import br.com.zup.casadocodigo.web.controller.dto.validation.cliente.ValidaReferenciasCliente;
import br.com.zup.casadocodigo.web.controller.dto.validation.cliente.VerificaEstadoPertenceAoPaisSelecionado;
import br.com.zup.casadocodigo.web.controller.dto.validation.cliente.ValidaObrigatoriedadeEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class FactoryClienteValidator extends AbstractFactoryClienteValidator{

    private final ClienteRepository clienteRepository;
    private final EstadoRepository estadoRepository;
    private final PaisRepository paisRepository;

    @Autowired
    public FactoryClienteValidator(ClienteRepository clienteRepository,
                                   EstadoRepository estadoRepository,
                                   PaisRepository paisRepository){
        this.clienteRepository = clienteRepository;
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Validator proibeDocumentoDuplicado() {
        return new ProibeDocumentoDuplicado(this.clienteRepository);
    }

    @Override
    public Validator validaReferenciasCliente() {
        return new ValidaReferenciasCliente(this.paisRepository,this.estadoRepository);
    }
    @Override
    public Validator validaObrigatoriedadeEstado() {
        return new ValidaObrigatoriedadeEstado(this.paisRepository);
    }

    @Override
    public Validator verificaEstadoPertenceAoPaisSelecionado() {
        return new VerificaEstadoPertenceAoPaisSelecionado(this.estadoRepository);
    }
}