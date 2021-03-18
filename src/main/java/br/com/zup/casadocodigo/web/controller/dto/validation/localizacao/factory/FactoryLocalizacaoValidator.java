package br.com.zup.casadocodigo.web.controller.dto.validation.localizacao.factory;

import br.com.zup.casadocodigo.data.repository.EstadoRepository;
import br.com.zup.casadocodigo.data.repository.PaisRepository;
import br.com.zup.casadocodigo.web.controller.dto.validation.localizacao.ProibeEstadoDuplicadoPorPais;
import br.com.zup.casadocodigo.web.controller.dto.validation.localizacao.ValidaReferenciasEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class FactoryLocalizacaoValidator extends AbstractFactoryLocalizacaoValidator{

    private final EstadoRepository estadoRepository;
    private final PaisRepository paisRepository;

    @Autowired
    public FactoryLocalizacaoValidator(EstadoRepository estadoRepository,
                                       PaisRepository paisRepository){
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Validator proibeEstadoDuplicadoPorPais() {
        return new ProibeEstadoDuplicadoPorPais(this.estadoRepository);
    }

    @Override
    public Validator validaReferenciasEstado() {
        return new ValidaReferenciasEstado(this.paisRepository);
    }

}