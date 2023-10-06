package com.ternova.restapi.restapi.validate;

import com.ternova.restapi.restapi.utils.CommonsConstant;
import com.ternova.restapi.restapi.utils.MessageEnumValidate;
import com.ternova.restapi.restapi.utils.NumberUtility;
import com.ternova.restapi.restapi.exception.models.BusinessException;
import com.ternova.restapi.restapi.exception.models.Error;
import jakarta.validation.*;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class ValidateModels {

    @Value("${constrain.validator.payload}")
    private String validatePayload;

    public void validateModel(Object object){

        Validator validator = createValidator(this.validatePayload);
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        Error error = new Error();

        for(ConstraintViolation<?> violation: violations) {

            error.setCode(HttpStatus.BAD_REQUEST.value()+"");
            error.setTitle(CommonsConstant.APPLICATION_NAME+" " +HttpStatus.BAD_REQUEST.getReasonPhrase());

            if (NumberUtility.isNumeric(violation.getMessage())) {

                String field = "";
                for (Path.Node node : violation.getPropertyPath()) {
                    field = node.getName();
                }

                error.setDetail(String.format(MessageEnumValidate.findMessageByCode(
                        violation.getMessage()).getDetail(), field));
            } else error.setDetail(violation.getMessage());

            if(!Objects.isNull(error.getCode()))
                throw new BusinessException(HttpStatus.BAD_REQUEST.value(), error.getTitle(), error);
        }
    }

    private static Validator createValidator(String payload){
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().constraintValidatorPayload(payload)
                .buildValidatorFactory();

        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        return validator;
    }
}
