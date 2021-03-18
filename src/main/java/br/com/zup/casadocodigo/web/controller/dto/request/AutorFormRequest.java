package br.com.zup.casadocodigo.web.controller.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorFormRequest {

    private Long id = 0L;
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(max = 400)
    private String descricao;

    @Deprecated
    public AutorFormRequest() {
    }

    public AutorFormRequest(Long id, @NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public AutorFormRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
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

}