package br.com.zup.casadocodigo.web.controller.dto.response.autor;

import br.com.zup.casadocodigo.data.domain.Autor;

public class AutorDetalhes {

    private Long id ;
    private String nome;
    private String descricao;

    @Deprecated
    public AutorDetalhes(){ }

    public AutorDetalhes(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public AutorDetalhes(Long id, String nome,  String descricao){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

}
