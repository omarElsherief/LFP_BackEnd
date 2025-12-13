package com.zanta.lfp.post.repository;

import com.zanta.lfp.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
