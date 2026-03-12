package com.junhee.spring_board_api.post.repository;

import com.junhee.spring_board_api.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
