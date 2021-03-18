package br.com.zup.casadocodigo.web.controller.dto.validation.categoria;

import br.com.zup.casadocodigo.data.domain.Categoria;
import br.com.zup.casadocodigo.data.repository.CategoriaRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.CategoriaFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Component
public class ProibidoNomeCategoriaDuplicado implements Validator {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProibidoNomeCategoriaDuplicado(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        CategoriaFormRequest request = (CategoriaFormRequest) target;
        Optional<Categoria> autorOptional = this.categoriaRepository.findByNome(request.getNome());

        if (autorOptional.isPresent()){
            errors.rejectValue("nome",null,"Já existe uma categoria com o nome informado");
        }
    }
}
