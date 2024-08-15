package com.bootcamp_2024_2.emazon.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    CATEGORY_NOT_FOUND("ERR_CATEGORY_001", "Category not found."),
    INVALID_CATEGORY("ERR_CATEGORY_002", "Invalid category parameters."),
    GENERIC_ERROR("ERR_GENERIC_001", "An unexpected error occurred.");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
