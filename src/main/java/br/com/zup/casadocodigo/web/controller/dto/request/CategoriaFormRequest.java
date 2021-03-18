package br.com.zup.casadocodigo.web.controller.dto.request;

import br.com.zup.casadocodigo.data.domain.Categoria;
import br.com.zup.casadocodigo.web.controller.dto.validation.generic.UniqueConstraints;
import javax.validation.constraints.NotBlank;

@UniqueConstraints(entityClass = Categoria.class, fieldsEntitiy = {"nome"})
public class CategoriaFormRequest {

    private Long id = 0L;
    @NotBlank
    private String nome;

    @Deprecated
    public CategoriaFormRequest(){ }

    public CategoriaFormRequest(Long id, @NotBlank String nome){
        this.id = id;
        this.nome = nome;
    }

    public CategoriaFormRequest(@NotBlank String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}
