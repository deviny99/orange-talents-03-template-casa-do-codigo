package br.com.zup.casadocodigo.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "estados")
public class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pais_ID", nullable=false)
    private Pais pais;

    @Deprecated
    public Estado(){ }

    public Estado(Integer id){
        this.id = id;
    }

    public Estado(Integer id,@NotBlank String nome,@NotNull Pais pais){
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public Estado(@NotBlank String nome,@NotNull Pais pais){
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(id, estado.id) && Objects.equals(nome, estado.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
