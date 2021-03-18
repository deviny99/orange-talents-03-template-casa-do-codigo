package br.com.zup.casadocodigo.web.controller.dto.request;

import br.com.zup.casadocodigo.data.domain.Cliente;
import br.com.zup.casadocodigo.data.domain.Documento;
import br.com.zup.casadocodigo.data.domain.Estado;
import br.com.zup.casadocodigo.data.domain.Pais;
import br.com.zup.casadocodigo.web.controller.dto.validation.generic.CPF_CNPJ;
import br.com.zup.casadocodigo.web.controller.dto.validation.generic.UniqueConstraints;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@UniqueConstraints(entityClass = Cliente.class, fieldsEntitiy = {"email"})
public class ClienteFormRequest {

    private Long id = 0L;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    @CPF_CNPJ
    private String documento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String endereco;
    private String complemento;
    @NotNull
    private Short paisID;
    private Integer estadoID;
    @NotBlank
    private String cep;
    @NotBlank
    private String telefone;

    @Deprecated
    public  ClienteFormRequest(){ }

    public ClienteFormRequest(Long id, @NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
                              @NotBlank @CPF_CNPJ String documento, @NotBlank String cidade, @NotBlank String endereco, String complemento,
                              @NotNull Short paisID,Integer estadoID, @NotBlank String cep, @NotBlank String telefone) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.cidade = cidade;
        this.endereco = endereco;
        this.complemento = complemento;
        this.paisID = paisID;
        this.estadoID = estadoID;
        this.cep = cep;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Cliente map(){
        return new Cliente(this.id,
                this.email,
                this.nome,
                this.sobrenome
                ,new Documento(this.documento),
                this.cidade,
                this.endereco,
                this.complemento,
                new Pais(this.paisID),
                this.getEstado(),
                this.cep,
                this.telefone);
    }

    private Estado getEstado(){
        return (this.estadoID!=null) ? new Estado(this.estadoID):null;
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

    public Short getPaisID() {
        return paisID;
    }

    public Integer getEstadoID() {
        return estadoID;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }
}
