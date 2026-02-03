package mads.exam.mapper;

import mads.exam.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> selectPosts(@Param("offset") int offset, @Param("limit") int limit);

    long countPosts();
}