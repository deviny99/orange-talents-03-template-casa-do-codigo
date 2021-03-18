package br.com.zup.casadocodigo.web.controller;

import br.com.zup.casadocodigo.data.domain.Livro;
import br.com.zup.casadocodigo.data.repository.LivroRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.LivroFormRequest;
import br.com.zup.casadocodigo.web.controller.dto.response.*;
import br.com.zup.casadocodigo.web.controller.dto.validation.livro.ValidaReferenciasLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private final LivroRepository livroRepository;
    private final ValidaReferenciasLivro validaReferenciasLivro;

    @Autowired
    public LivroController(LivroRepository livroRepository,
                           ValidaReferenciasLivro validaReferenciasLivro){
        this.livroRepository = livroRepository;
        this.validaReferenciasLivro = validaReferenciasLivro;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(this.validaReferenciasLivro);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarLivro(@RequestBody @Valid LivroFormRequest livroForm){

        Livro livroEntity = this.livroRepository.save(livroForm.map());
        return ResponseEntity.ok(new LivroDto(livroEntity));
    }

}