package br.com.zup.casadocodigo.web.controller.dto.validation.cliente;

import br.com.zup.casadocodigo.data.domain.Pais;
import br.com.zup.casadocodigo.data.repository.PaisRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.ClienteFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class ValidaObrigatoriedadeEstado implements Validator {


    private final PaisRepository paisRepository;

    @Autowired
    public ValidaObrigatoriedadeEstado(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
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
        Optional<Pais> optionalEntity = this.paisRepository.findByIdToLazy(request.getPaisID());

        if(optionalEntity.isPresent()){
            if(!optionalEntity.get().getEstados().isEmpty() && request.getEstadoID() == null){
                errors.rejectValue("estadoID",null,"O campo estadoID é obrigatório para esse pais");
            }
        }
    }

}
