package br.com.zup.casadocodigo.web.controller.dto.validation.autor;

import br.com.zup.casadocodigo.data.domain.Autor;
import br.com.zup.casadocodigo.data.repository.AutorRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.AutorFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class ProibeEmailDuplicadoAutorValidator implements Validator {

    private final AutorRepository autorRepository;

    @Autowired
    public ProibeEmailDuplicadoAutorValidator(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        AutorFormRequest request = (AutorFormRequest) target;
        Optional<Autor> autorOptional = this.autorRepository.findByEmail(request.getEmail());

        if (autorOptional.isPresent()){
            errors.rejectValue("email",null,"Já existe um(a) outro Autor(a) com o email "
                    +request.getEmail() +" cadastrado");
        }
    }
}
