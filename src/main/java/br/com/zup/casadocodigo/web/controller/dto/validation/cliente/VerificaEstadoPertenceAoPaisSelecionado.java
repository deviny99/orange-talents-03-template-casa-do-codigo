package br.com.zup.casadocodigo.web.controller.dto.validation.cliente;

import br.com.zup.casadocodigo.data.domain.Estado;
import br.com.zup.casadocodigo.data.repository.EstadoRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.ClienteFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class VerificaEstadoPertenceAoPaisSelecionado implements Validator {

    private EstadoRepository estadoRepository;

    @Autowired
    public VerificaEstadoPertenceAoPaisSelecionado(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
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
        Optional<Estado> optionalEntity = this.estadoRepository.findByPaisId(request.getPaisID());

        if(optionalEntity.isPresent()){
            if(!request.getPaisID().equals(optionalEntity.get().getPais().getId())){
                errors.rejectValue("estadoID",null,"O estado selecionado não pertence ao pais indicado.");
            }
        }
    }

}
