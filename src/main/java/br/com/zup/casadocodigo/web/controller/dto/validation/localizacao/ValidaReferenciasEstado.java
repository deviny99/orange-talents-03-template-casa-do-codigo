package br.com.zup.casadocodigo.web.controller.dto.validation.localizacao;

import br.com.zup.casadocodigo.data.domain.Pais;
import br.com.zup.casadocodigo.data.repository.PaisRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.EstadoFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class ValidaReferenciasEstado implements Validator {

    private PaisRepository paisRepository;

    @Autowired
    public ValidaReferenciasEstado(PaisRepository paisRepository){
        this.paisRepository = paisRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EstadoFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        EstadoFormRequest request = (EstadoFormRequest) target;
        Optional<Pais> paisOptionalEntity = this.paisRepository.findById(request.getPaisID());

        if (!paisOptionalEntity.isPresent()){
            errors.rejectValue("paisID",null, "Não existe um Pais cadastrado com o id informado.");
        }
    }
}
