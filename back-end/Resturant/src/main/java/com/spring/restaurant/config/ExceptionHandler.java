package com.spring.restaurant.config;

import com.spring.restaurant.service.bundle.BundleTranslatorService;
import com.spring.restaurant.service.dto.BundleMessage.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<ExceptionResponseDto> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.ok(new ExceptionResponseDto(HttpStatus.NOT_ACCEPTABLE, BundleTranslatorService.getBundleMessageinEnglishAndArabic(ex.getMessage())));
    }
}
