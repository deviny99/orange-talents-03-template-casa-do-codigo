package br.com.zup.casadocodigo.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome" , nullable = false)
    private String nome;
    @Column(name = "email" , nullable = false, unique = true)
    private String email;
    @Column(name = "descricao" , nullable = false, length = 400)
    private String descricao;
    @Column(name = "momentoRegistro" , nullable = false)
    private LocalDateTime momentoRegistro = LocalDateTime.now();


    @Deprecated
    public Autor(){

    }

    public Autor(Long id, @NotNull @NotBlank String nome,@NotNull @NotBlank String email,@NotNull @NotBlank String descricao){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.momentoRegistro = LocalDateTime.now();
    }

    public Autor(@NotNull @NotBlank String nome,@NotNull @NotBlank String email,@NotNull @NotBlank String descricao){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.momentoRegistro = LocalDateTime.now();
    }

    public Autor(Long autorID) {
        this.id = autorID;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getMomentoRegistro() {
        return momentoRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id) && Objects.equals(email, autor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}