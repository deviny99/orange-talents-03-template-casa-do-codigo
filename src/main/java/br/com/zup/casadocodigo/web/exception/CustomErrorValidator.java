package br.com.zup.casadocodigo.web.exception;

import java.util.Map;

public class CustomErrorValidator extends RuntimeException{

    private final Map<String,String> campos;

    public CustomErrorValidator(Map<String,String> campos, String message){
        super(message);
        this.campos = campos;
    }

    public Map<String, String> getCampos() {
        return campos;
    }
}
