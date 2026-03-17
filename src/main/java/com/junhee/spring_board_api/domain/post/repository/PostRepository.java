package com.junhee.spring_board_api.domain.post.repository;

import com.junhee.spring_board_api.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findByTitleContaining(String title, Pageable pageable);
}
