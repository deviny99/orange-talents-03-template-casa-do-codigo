package br.com.zup.casadocodigo.web.controller.dto.response;

import br.com.zup.casadocodigo.data.domain.Categoria;

public class CategoriaDto {

    private Long id;
    private String nome;

    @Deprecated
    public CategoriaDto(){ }

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}