package br.com.zup.casadocodigo.web.controller.dto.response.localizacao;

import br.com.zup.casadocodigo.data.domain.Estado;
import java.util.Set;
import java.util.stream.Collectors;

public class EstadoDto {

    private Integer id;
    private String nome;
    private String pais;

    @Deprecated
    public EstadoDto() { }

    public EstadoDto(Estado estado){
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.pais = estado.getPais().getNome();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }

    public static Set<EstadoDto> convertList(Set<Estado> estados){

        return estados.stream().map(EstadoDto::new).collect(Collectors.toSet());
    }
}
