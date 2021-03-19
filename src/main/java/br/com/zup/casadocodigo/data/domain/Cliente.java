package br.com.zup.casadocodigo.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;
    @NotNull
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,unique = true)
    private Documento documento;
    @Column(name = "cidade", nullable = false)
    private String cidade;
    @Column(name = "endereco", nullable = false)
    private String endereco;
    @Column(name = "complemento", nullable = false)
    private String complemento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pais", nullable = false)
    private Pais pais;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private Estado estado;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Deprecated
    public Cliente(){

    }

    public Cliente(Long id, String email, String nome, String sobrenome,
                   Documento documento, String cidade, String endereco,
                   String complemento, Pais pais, Estado estado, String cep,
                   String telefone) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.cidade = cidade;
        this.endereco = endereco;
        this.complemento = complemento;
        this.pais = pais;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Documento getDocumento() {
        return documento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }
}
