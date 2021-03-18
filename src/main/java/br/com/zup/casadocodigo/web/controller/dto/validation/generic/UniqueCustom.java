package br.com.zup.casadocodigo.web.controller.dto.validation.generic;

import br.com.zup.casadocodigo.web.exception.CustomErrorValidator;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Transactional(readOnly = true)
public class UniqueCustom implements ConstraintValidator<UniqueConstraints,Object> {

    @PersistenceContext
    private EntityManager manager;

    private String [] fieldsEntitiy;
    private Class<?> entityClass;
    private String idEntityFieldName;
    private String idValueText;
    private Type typeID;

    @Override
    public void initialize(UniqueConstraints constraintAnnotation) {
        this.fieldsEntitiy = constraintAnnotation.fieldsEntitiy();
        this.idEntityFieldName = constraintAnnotation.idEntityFieldName();
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(Object objetoAnotado, ConstraintValidatorContext constraintValidatorContext) {
        return validar(objetoAnotado);
    }

    private boolean validar(Object objetoAnotado){
        this.initFields(objetoAnotado);

        final Map<String,String> campoErros = new HashMap<>();

        Arrays.stream(fieldsEntitiy).forEach(campo -> {
            try
            {
                Field field = objetoAnotado.getClass().getDeclaredField(campo);

                if (isExistsQueryToInsertAndUpdate(field, objetoAnotado))
                {
                    campoErros.put(field.getName(),"Esse campo é único");
                }

            } catch (NoSuchFieldException | IllegalAccessException  e) {
                e.printStackTrace();
            }
        });

        if (!campoErros.isEmpty()){
            throw new CustomErrorValidator(campoErros,"Campos unicos");
        }
        return campoErros.isEmpty();
    }

    @Transactional(readOnly = true )
    private boolean isExistsQueryToInsertAndUpdate(Field field,Object objetoAnotado) throws IllegalAccessException, NoSuchFieldException {

        Query query = this.manager.createQuery("SELECT 1 FROM "+this.entityClass.getSimpleName()+" e WHERE "+field.getName()+"= :valor and "+idName(objetoAnotado)+" != :identificado");
        field.setAccessible(true);
        query.setParameter("valor",field.get(objetoAnotado));
        field.setAccessible(false);
        query.setParameter("identificado", this.getIdValue());

        if(query.getResultList().isEmpty() || query.getResultList() == null)
            return false;
        else
            return true;
    }

    private String idName(Object objetoAnotado) throws NoSuchFieldException {
        Field idField = objetoAnotado.getClass().getDeclaredField(this.idEntityFieldName);
        return idField.getName();
    }

    private Object getIdValue() {

        if (Long.class.equals(this.typeID)) {
            return Long.parseLong(this.idValueText);
        } else if (Short.class.equals(this.typeID)) {
            return Short.parseShort(this.idValueText);
        } else if (Integer.class.equals(this.typeID)) {
            return Integer.parseInt(this.idValueText);
        }else if (String.class.equals(this.typeID)) {
            return String.valueOf(this.idValueText);
        }
        return null;
    }

    private void initFields(Object objetoAnotado) {
        try {
            Field idField = objetoAnotado.getClass().getDeclaredField(this.idEntityFieldName);
            Type type = idField.getType();
            idField.setAccessible(true);
            String valor = idField.get(objetoAnotado).toString();
            idField.setAccessible(false);

            if (type != null && valor != null) {
                this.typeID = type;
                this.idValueText = valor;
            }
        }catch (NoSuchFieldException | IllegalAccessException se){ }
    }
}
