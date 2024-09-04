package com.bootcamp_2024_2.emazon.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseTest {

    @Test
    void errorResponseBuilderTest() {
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse errorResponse = new ErrorResponse("404", "Not Found", Collections.singletonList("Category not found"), now);

        assertEquals("404", errorResponse.getCode());
        assertEquals("Not Found", errorResponse.getMessage());
        assertEquals(1, errorResponse.getDetails().size());
        assertEquals(now, errorResponse.getTimestamp());
    }

    @Test
    void errorResponseSettersTest() {
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("500");
        errorResponse.setMessage("Internal Server Error");
        errorResponse.setDetails(Collections.singletonList("Unexpected error occurred"));
        errorResponse.setTimestamp(now);

        assertEquals("500", errorResponse.getCode());
        assertEquals("Internal Server Error", errorResponse.getMessage());
        assertEquals(1, errorResponse.getDetails().size());
        assertEquals(now, errorResponse.getTimestamp());
    }
}

