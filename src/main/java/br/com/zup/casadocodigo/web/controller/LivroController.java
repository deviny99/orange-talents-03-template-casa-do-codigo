package br.com.zup.casadocodigo.web.controller;

import br.com.zup.casadocodigo.data.domain.Livro;
import br.com.zup.casadocodigo.data.repository.LivroRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.LivroFormRequest;
import br.com.zup.casadocodigo.web.controller.dto.response.*;
import br.com.zup.casadocodigo.web.controller.dto.validation.livro.ValidaReferenciasLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static org.springframework.http.ResponseEntity.ok;

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
        return ok(new LivroDto(livroEntity));
    }

    @GetMapping
    public ResponseEntity<?> listaLivro(@PageableDefault(size = 10, direction = Sort.Direction.ASC, page = 0,
            sort = "titulo") Pageable pageable,
                                        @RequestParam(value = "titulo", required = false) String titulo){
        Page<LivroResponseList> livros;

        if(titulo == null){
            livros =  LivroResponseList.convertList(this.livroRepository.findAll(pageable));
        }else{
            livros =  LivroResponseList.convertList(this.livroRepository.findByTituloLike(titulo,pageable));
        }
        return ok(livros);
    }

}