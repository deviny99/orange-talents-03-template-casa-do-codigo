package br.com.zup.casadocodigo.web.controller.dto.validation.localizacao.factory;

import org.springframework.validation.Validator;

public abstract class AbstractFactoryLocalizacaoValidator {

    public abstract Validator proibeEstadoDuplicadoPorPais();
    public abstract Validator validaReferenciasEstado();

}