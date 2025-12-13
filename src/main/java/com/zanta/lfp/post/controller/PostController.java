package com.zanta.lfp.post.controller;


import com.zanta.lfp.post.dto.CreatePostDto;
import com.zanta.lfp.post.service.PostService;
import com.zanta.lfp.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts() {
        return postService.getAllPosts();
    }

    // Get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }


    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostDto dto,
                                        @AuthenticationPrincipal User user) {
        return postService.createPost(dto, user.getId());
    }

    // Delete post by ID (only owner can delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id,
                                        @AuthenticationPrincipal User user) {
        return postService.deletePost(id, user.getId());
    }
}





















/*
package com.zanta.lfp.post.controller;

import com.zanta.lfp.post.dto.CreatePostDto;
import com.zanta.lfp.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Create a post
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostDto dto, Authentication auth) {
        // Get logged-in user ID from auth (adjust if using JWT or session)
        int ownerId = Integer.parseInt(auth.getName());
        return postService.createPost(dto, ownerId);
    }

    // Get all posts
    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts() {
        return postService.getAllPosts();
    }

    // Get single post
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // Delete a post (only owner)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Authentication auth) {
        int userId = Integer.parseInt(auth.getName());
        return postService.deletePost(id, userId);
    }
}
*/