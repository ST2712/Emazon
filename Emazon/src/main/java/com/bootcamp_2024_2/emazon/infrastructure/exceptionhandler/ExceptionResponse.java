package com.bootcamp_2024_2.emazon.infrastructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {

    CATEGORY_NOT_FOUND("ERR_CATEGORY_001", "Category not found."),
    INVALID_CATEGORY("ERR_CATEGORY_002", "Invalid category parameters."),
    BRAND_NOT_FOUND("ERR_BRAND_001", "Brand not found."),
    INVALID_BRAND("ERR_BRAND_002", "Invalid brand parameters."),
    GENERIC_ERROR("ERR_GENERIC_001", "An unexpected error occurred.");

    private final String code;
    private final String message;

    ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
