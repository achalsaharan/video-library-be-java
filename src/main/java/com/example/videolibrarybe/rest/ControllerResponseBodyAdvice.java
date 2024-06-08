package com.example.videolibrarybe.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

//@ControllerAdvice
@Slf4j
public class ControllerResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return Objects.requireNonNull(returnType.getMethod()).getReturnType().equals(ResponseEntity.class);

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        assert body != null;
        log.info(body.toString());
        if(body instanceof Exception) {
            return body;
        }
        return new RestResponseSuccessBody(body);

//        if(body instanceof ResponseEntity) {
//            RestResponseSuccess restResponseSuccess = new RestResponseSuccess(((ResponseEntity<?>) body).getBody());
//            return new ResponseEntity<>(restResponseSuccess, ((ResponseEntity<?>) body).getStatusCode());
//        } else {
//            log.error("response not wrapper in ResponseEntity {}", body);
//            return new RestResponseError("error occurred");
////            return new ResponseEntity<>(new RestResponseError("error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
