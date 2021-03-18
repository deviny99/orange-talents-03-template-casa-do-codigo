package br.com.zup.casadocodigo.web.controller.dto.validation.livro;

import br.com.zup.casadocodigo.data.domain.Autor;
import br.com.zup.casadocodigo.data.domain.Categoria;
import br.com.zup.casadocodigo.data.repository.AutorRepository;
import br.com.zup.casadocodigo.data.repository.CategoriaRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.LivroFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Repository
public class ValidaReferenciasLivro implements Validator {

    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ValidaReferenciasLivro(AutorRepository autorRepository, CategoriaRepository categoriaRepository){
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LivroFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        LivroFormRequest request = (LivroFormRequest) target;
        Optional<Categoria> categoriaOptionalEntity = this.categoriaRepository.findById(request.getCategoriaID());
        Optional<Autor> autorOptionalEntity = this.autorRepository.findById(request.getAutorID());
        this.validarReferenciaAutor(autorOptionalEntity,errors);
        this.validarReferenciaCategoria(categoriaOptionalEntity,errors);

    }

    private void validarReferenciaCategoria(Optional<Categoria> optionalCategoria, Errors errors){
        if(!optionalCategoria.isPresent()){
            errors.rejectValue("categoriaID",null, "Não existe uma categoria cadastrado com o id informado.");
        }
    }

    private void validarReferenciaAutor(Optional<Autor> optionalAutor, Errors errors){
        if(!optionalAutor.isPresent()){
            errors.rejectValue("autorID",null, "Não existe um autor cadastrado com o id informado.");
        }
    }
}
