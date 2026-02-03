package mads.exam.dto;

import java.util.List;
import java.util.Map;

public record ErrorResponse(
    int status,
    String message,
    Map<String, List<String>> field_errors
) {
}