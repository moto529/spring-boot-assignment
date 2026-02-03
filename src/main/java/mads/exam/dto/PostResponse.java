package mads.exam.dto;

import java.time.Instant;

public record PostResponse (
    Long id,
    String title,
    Instant createdAt
) {
}