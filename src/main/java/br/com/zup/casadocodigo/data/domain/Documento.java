package br.com.zup.casadocodigo.data.domain;

import br.com.zup.casadocodigo.web.controller.dto.validation.generic.CPF_CNPJ;
import br.com.zup.casadocodigo.web.exception.ExceptionController;
import javax.persistence.*;
import static br.com.zup.casadocodigo.utils.CpfCnpjFormatter.removeCaracteresEspeciais;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoPessoa tipoPessoa;
    @CPF_CNPJ
    @Column(unique = true,nullable = false)
    private String registro;

    @Deprecated
    public  Documento(){ }

    public Documento(Long id, TipoPessoa tipoPessoa, String registro) {
        this.id = id;
        this.registro = registro;
        this.tipoPessoa = validaTipoPessoa(this.registro);
    }

    public Documento(String registro) {
        this.registro = registro;
        this.tipoPessoa = validaTipoPessoa(this.registro);
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public Long getId() {
        return id;
    }

    public String getRegistro() {
        return removeCaracteresEspeciais(this.registro);
    }

    private TipoPessoa validaTipoPessoa(String registro){

        String registroSemCaracteresEspeciais = removeCaracteresEspeciais(this.registro);
        if(registroSemCaracteresEspeciais.length() == 11){
            return TipoPessoa.FISICA;
        }else if(registroSemCaracteresEspeciais.length() == 14){
            return TipoPessoa.JURIDICA;
        }
        throw ExceptionController.badRequest("Documento informado é invalido.");
    }
}