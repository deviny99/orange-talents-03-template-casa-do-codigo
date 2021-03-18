package br.com.zup.casadocodigo.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "paises")
public class Pais implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @NotBlank
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais",targetEntity = Estado.class)
    private Set<Estado> estados = new HashSet<>();

    @Deprecated
    public Pais(){ }

    public Pais(Short id,@NotBlank String nome, Set<Estado> estados) {
        this.id = id;
        this.nome = nome;
        this.estados = estados;
    }

    public Pais(Short id) {
        this.id = id;
    }

    public Pais(Short id,@NotBlank String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pais(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Short getId() {
        return id;
    }

    public Set<Estado> getEstados() {
        return estados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(id, pais.id) && Objects.equals(nome, pais.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
