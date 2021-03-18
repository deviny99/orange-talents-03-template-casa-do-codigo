package br.com.zup.casadocodigo.web.controller.dto.response.localizacao;

import br.com.zup.casadocodigo.data.domain.Pais;
import java.util.HashSet;
import java.util.Set;

public class PaisDto {

    private Short id;
    private String nome;
    private Set<EstadoDto> estados = new HashSet<>();

    @Deprecated
    public  PaisDto(){ }

    public PaisDto(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
        this.estados = EstadoDto.convertList(pais.getEstados());
    }

    public String getNome() {
        return nome;
    }

    public Short getId() {
        return id;
    }

    public Set<EstadoDto> getEstados() {
        return this.estados;
    }
}
