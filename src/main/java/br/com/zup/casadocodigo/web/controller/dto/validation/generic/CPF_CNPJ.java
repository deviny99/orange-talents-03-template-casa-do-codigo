package br.com.zup.casadocodigo.web.controller.dto.validation.generic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = Cpf_CnpjValidation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CPF_CNPJ {

    String message() default "Documento invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
