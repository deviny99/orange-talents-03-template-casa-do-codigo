package br.com.zup.casadocodigo.web.controller.dto.response.client;

import br.com.zup.casadocodigo.data.domain.Documento;
import br.com.zup.casadocodigo.data.domain.TipoPessoa;

public class DocumentoDto {

    private TipoPessoa tipoPessoa;
    private String registro;

    public DocumentoDto(Documento documento) {
        this.tipoPessoa = documento.getTipoPessoa();
        this.registro = documento.getRegistro();
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public String getRegistro() {
        return registro;
    }
}
