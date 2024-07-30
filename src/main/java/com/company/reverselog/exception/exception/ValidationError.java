package com.company.reverselog.exception.exception;

import java.util.Map;

public class ValidationError extends StandardError {
    private Map<String, String> fieldErrors;

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
