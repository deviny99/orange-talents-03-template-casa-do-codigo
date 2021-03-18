package br.com.zup.casadocodigo.web.controller;

import br.com.zup.casadocodigo.data.domain.Categoria;
import br.com.zup.casadocodigo.data.repository.CategoriaRepository;
import br.com.zup.casadocodigo.web.controller.dto.request.CategoriaFormRequest;
import br.com.zup.casadocodigo.web.controller.dto.response.categoria.CategoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid CategoriaFormRequest categoriaFormRequest){

        Categoria categoriaEntity = this.categoriaRepository.save(new Categoria(categoriaFormRequest.getId(),categoriaFormRequest.getNome()));
        return ResponseEntity.ok(new CategoriaDto(categoriaEntity));

    }

}