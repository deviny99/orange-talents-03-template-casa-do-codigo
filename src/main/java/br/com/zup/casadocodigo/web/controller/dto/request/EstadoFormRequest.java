package br.com.zup.casadocodigo.web.controller.dto.request;

import br.com.zup.casadocodigo.data.domain.Estado;
import br.com.zup.casadocodigo.data.domain.Pais;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoFormRequest {

    private Integer id = 0;
    @NotBlank
    private String nome;
    @NotNull
    private Short paisID;

    @Deprecated
    public EstadoFormRequest() { }

    public EstadoFormRequest(Integer id, @NotBlank String nome, @NotNull Short paisID) {
        this.id = id;
        this.nome = nome;
        this.paisID = paisID;
    }

    public EstadoFormRequest(@NotBlank String nome, @NotNull Short paisID) {
        this.nome = nome;
        this.paisID = paisID;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public Short getPaisID() {

        return paisID;
    }

    public Estado map(){
        return new Estado(this.id,this.nome,new Pais(this.paisID));
    }
}
