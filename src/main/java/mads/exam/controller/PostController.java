package mads.exam.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Min;
import mads.exam.dto.PostResponse;
import mads.exam.dto.SuccessListResponse;
import mads.exam.service.PostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@Validated
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
 
    @GetMapping
    public SuccessListResponse<PostResponse> getPosts(
            @RequestParam(name = "page", required = false) String pageRaw
    ) {
        int page = parsePageOrThrow(pageRaw);
        return postService.getPosts(page);
    }

    private int parsePageOrThrow(String pageRaw) {
        if (pageRaw == null) {
            return 1;
        }

        if (pageRaw.isBlank()) {
            throw new ConstraintViolationException("Invalid request", java.util.Set.of());
        }

        int page;

        try {
            page = Integer.parseInt(pageRaw);
        } catch (NumberFormatException e) {
            throw new ConstraintViolationException("Invalid request", java.util.Set.of());
        }

        if (page < 1) {
            throw new ConstraintViolationException("Invalid request", java.util.Set.of());
        }

        return page;
    }
}
