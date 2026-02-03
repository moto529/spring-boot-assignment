package mads.exam.service;

import java.util.List;
import mads.exam.domain.Post;
import mads.exam.dto.PostResponse;
import mads.exam.dto.SuccessListResponse;
import mads.exam.mapper.PostMapper;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private static final int DEFAULT_SIZE = 5;

    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public SuccessListResponse<PostResponse> getPosts(int page) {
        int size = DEFAULT_SIZE;
        int offset = (page - 1) * size;

        long totalElements = postMapper.countPosts();
        int totalPages = (int) Math.ceil(totalElements / (double) size);

        List<Post> posts = postMapper.selectPosts(offset, size);

        List<PostResponse> postResponses = posts.stream()
                .map(p -> new PostResponse(p.getId(), p.getTitle(), p.getCreatedAt()))
                .toList();

        return new SuccessListResponse<>(
                postResponses,
                page,
                size,
                totalElements,
                totalPages
        );
    }
}
