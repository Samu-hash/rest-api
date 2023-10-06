package com.ternova.restapi.restapi.exception.imp;

import com.ternova.restapi.restapi.utils.CommonsConstant;
import com.ternova.restapi.restapi.utils.MessageEnumValidate;
import com.ternova.restapi.restapi.exception.models.BusinessException;
import com.ternova.restapi.restapi.models.response.RestApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class ThrowException {

    private static final Logger logger = LoggerFactory.getLogger(ThrowException.class);

    public static <T extends RestApiResponse> void throwException(MessageEnumValidate enums, T classResponse){
        logger.info("----- Init throwException, params [{}], [{}]", enums, classResponse);
        Integer code = Integer.parseInt(enums.getCode());
        if(Objects.isNull(classResponse))
            throw new BusinessException(code,
                    String.format(CommonsConstant.APP_CONNECTION_FAILED, enums.getDetail()), null);

        if(Objects.isNull(classResponse.getData()))
            throw new BusinessException(code, enums.getTitle(), classResponse.getError());
    }
}
