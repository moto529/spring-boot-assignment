package mads.exam.dto;

import java.util.List;

public record SuccessListResponse<T>(
        List<T> posts,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}