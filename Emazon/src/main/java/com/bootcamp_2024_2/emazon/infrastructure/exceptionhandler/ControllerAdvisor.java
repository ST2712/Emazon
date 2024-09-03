package com.bootcamp_2024_2.emazon.infrastructure.exceptionhandler;

import com.bootcamp_2024_2.emazon.domain.model.ErrorResponse;
import com.bootcamp_2024_2.emazon.infrastructure.exception.CategoryNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.bootcamp_2024_2.emazon.infrastructure.exceptionhandler.ExceptionResponse.CATEGORY_NOT_FOUND;
import static com.bootcamp_2024_2.emazon.infrastructure.exceptionhandler.ExceptionResponse.INVALID_CATEGORY;

@RestControllerAdvice
public class ControllerAdvisor {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFoundException() {
        return ErrorResponse.builder()
                .code(CATEGORY_NOT_FOUND.getCode())
                .message(CATEGORY_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result  = exception.getBindingResult();
        return ErrorResponse.builder()
                .code(INVALID_CATEGORY.getCode())
                .message(INVALID_CATEGORY.getMessage())
                .details(result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception) {
        return ErrorResponse.builder()
                .code(ExceptionResponse.GENERIC_ERROR.getCode())
                .message(ExceptionResponse.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
