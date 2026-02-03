package mads.exam.exception;

import java.util.List;
import java.util.Map;

public class BadRequestException extends RuntimeException {
    private final Map<String, List<String>> fieldErrors;

    public BadRequestException(Map<String, List<String>> fieldErrors) {
        super("Invalid request");
        this.fieldErrors = fieldErrors;
    }

    public Map<String, List<String>> getFieldErrors() {
        return fieldErrors;
    }
}
