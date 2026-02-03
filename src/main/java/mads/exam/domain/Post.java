package mads.exam.domain;

import lombok.Getter;
import java.time.Instant;

@Getter
public class Post {
    private Long id;
    private String title;
    private Instant createdAt;
}