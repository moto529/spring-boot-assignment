package mads.exam.controller;

import mads.exam.dto.PostResponse;
import mads.exam.dto.SuccessListResponse;
import mads.exam.service.PostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import mads.exam.exception.BadRequestException;
import java.util.List;
import java.util.Map;

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
        // 未指定ならデフォルト 1
        if (pageRaw == null) {
            return 1;
        }

        if (pageRaw.isBlank()) {
            throw new BadRequestException(Map.of("page", List.of("must be a valid value")));
        }

        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (NumberFormatException e) {
            throw new BadRequestException(Map.of("page", List.of("must be a valid value")));
        }

        if (page < 1) {
            throw new BadRequestException(Map.of("page", List.of("must be greater than or equal to 1")));
        }

        return page;
    }
}
