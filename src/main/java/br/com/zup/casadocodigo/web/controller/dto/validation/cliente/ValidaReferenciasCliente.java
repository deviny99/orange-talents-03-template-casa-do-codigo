package br.com.zup.casadocodigo.web.controller.dto.validation.cliente;

import br.com.zup.casadocodigo.data.domain.Estado;
import br.com.zup.casadocodigo.data.domain.Pais;
import br.com.zup.casadocodigo.data.repository.EstadoRepository;
import br.com.zup.casadocodigo.data.repository.PaisRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.ClienteFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class ValidaReferenciasCliente implements Validator {

    private final PaisRepository paisRepository;
    private final EstadoRepository estadoRepository;

    @Autowired
    public ValidaReferenciasCliente(PaisRepository paisRepository, EstadoRepository estadoRepository){
        this.paisRepository = paisRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ClienteFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        ClienteFormRequest request = (ClienteFormRequest) target;
        Optional<Pais> paisOptionalEntity = this.paisRepository.findById(request.getPaisID());

        this.validarReferenciaPais(paisOptionalEntity, errors);

        if(request.getEstadoID()!= null){
            Optional<Estado> estadoOptionalEntity = this.estadoRepository.findById(request.getEstadoID());
            this.validarReferenciaEstado(estadoOptionalEntity, errors);
        }

    }

    private void validarReferenciaEstado(Optional<Estado> optionalEstado, Errors errors){
        if(!optionalEstado.isPresent()){
            errors.rejectValue("estadoID",null, "Não existe um estado cadastrado com o id informado.");
        }
    }

    private void validarReferenciaPais(Optional<Pais> optionalPais, Errors errors){
        if(!optionalPais.isPresent()){
            errors.rejectValue("paisID",null, "Não existe um pais cadastrado com o id informado.");
        }
    }
}
