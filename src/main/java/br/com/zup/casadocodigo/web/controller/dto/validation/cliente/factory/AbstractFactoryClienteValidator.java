package br.com.zup.casadocodigo.web.controller.dto.validation.cliente.factory;

import org.springframework.validation.Validator;

public abstract class AbstractFactoryClienteValidator {

    public abstract Validator proibeDocumentoDuplicado();
    public abstract Validator validaReferenciasCliente();
    public abstract Validator verificaEstadoPertenceAoPaisSelecionado();
    public abstract Validator validaObrigatoriedadeEstado();

}
