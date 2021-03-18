package br.com.zup.casadocodigo.web.controller.dto.response;

import br.com.zup.casadocodigo.data.domain.Autor;
import java.time.LocalDateTime;

public class AutorDto {

    private Long id ;
    private String nome;
    private String email;
    private String descricao;
    private LocalDateTime momentoRegistro;

    @Deprecated
    public AutorDto(){ }

    public AutorDto(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.email = autor.getEmail();
        this.descricao = autor.getDescricao();
        this.momentoRegistro = autor.getMomentoRegistro();
    }

    public String getEmail() {
        return email;
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

    public LocalDateTime getMomentoRegistro() {
        return momentoRegistro;
    }

}