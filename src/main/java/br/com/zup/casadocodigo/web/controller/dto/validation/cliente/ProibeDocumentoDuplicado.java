package br.com.zup.casadocodigo.web.controller.dto.validation.cliente;

import br.com.zup.casadocodigo.data.domain.Cliente;
import br.com.zup.casadocodigo.data.repository.ClienteRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.ClienteFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class ProibeDocumentoDuplicado implements Validator {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ProibeDocumentoDuplicado(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ClienteFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors()){
            return;
        }

        ClienteFormRequest request = (ClienteFormRequest) target;
        Optional<Cliente> optionalEntity = clienteRepository.findByDocumento(request.getDocumento());

        if(optionalEntity.isPresent()){
            if(!optionalEntity.get().getId().equals(request.getId())){
                errors.rejectValue("documento",null,"O documento inserido já foi cadastrado");
            }
        }

    }

}
