package br.com.zup.casadocodigo.web.controller.dto.request;

import br.com.zup.casadocodigo.data.domain.Pais;
import br.com.zup.casadocodigo.web.controller.dto.validation.generic.UniqueConstraints;
import javax.validation.constraints.NotBlank;

@UniqueConstraints(entityClass = Pais.class, fieldsEntitiy = {"nome"})
public class PaisFormRequest {

    private Short id = 0;
    @NotBlank
    private String nome;

    @Deprecated
    public PaisFormRequest() { }

    public PaisFormRequest(Short id, @NotBlank String nome) {
        this.id = id;
        this.nome = nome;
    }

    public PaisFormRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Short getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
