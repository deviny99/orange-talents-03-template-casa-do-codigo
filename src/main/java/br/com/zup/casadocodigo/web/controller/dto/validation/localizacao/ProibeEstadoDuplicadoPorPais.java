package br.com.zup.casadocodigo.web.controller.dto.validation.localizacao;

import br.com.zup.casadocodigo.data.domain.Estado;
import br.com.zup.casadocodigo.data.repository.EstadoRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.EstadoFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class ProibeEstadoDuplicadoPorPais implements Validator {


    private final EstadoRepository estadoRepository;

    @Autowired
    public ProibeEstadoDuplicadoPorPais(EstadoRepository estadoRepository) {
         this.estadoRepository = estadoRepository;
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
        EstadoFormRequest  request = (EstadoFormRequest) target;
        Optional<Estado> estadoOptional = this.estadoRepository.findByNomeAndPais(request.getNome(),request.getPaisID());

        if (estadoOptional.isPresent()){
            errors.rejectValue("nome",null, "Já existe um estado cadastrado com esse nome no pais informado");
        }
    }
}
