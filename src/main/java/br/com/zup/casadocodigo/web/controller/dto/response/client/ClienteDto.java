package br.com.zup.casadocodigo.web.controller.dto.response.client;

import br.com.zup.casadocodigo.data.domain.Cliente;

public class ClienteDto {

    private Long id;
    private String nome;
    private DocumentoDto documento;


    public ClienteDto(Cliente clienteEntity) {
        this.id = clienteEntity.getId();
        this.nome = clienteEntity.getNome();
        this.documento = new DocumentoDto(clienteEntity.getDocumento());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public DocumentoDto getDocumento() {
        return documento;
    }
}
