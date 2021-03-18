package br.com.zup.casadocodigo.web.controller.dto.validation.generic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Constraint(validatedBy = UniqueCustom.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraints {

    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] fieldsEntitiy() default {};
    Class<?> entityClass();
    String idEntityFieldName() default "id";

}
