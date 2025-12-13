package com.zanta.lfp.post.service;

import com.zanta.lfp.user.Dto.UserDto;
import com.zanta.lfp.game.model.Game;
import com.zanta.lfp.user.model.User;
import com.zanta.lfp.post.dto.CreatePostDto;
import com.zanta.lfp.post.dto.PostDto;
import com.zanta.lfp.post.model.Post;
import com.zanta.lfp.post.repository.PostRepository;
import com.zanta.lfp.game.repository.GameRepository;
import com.zanta.lfp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public ResponseEntity<?> createPost(CreatePostDto dto ,Long ownerId){
        //get user
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found (we don't have USER with this ID)"));
        //get game
        Game game = gameRepository.findById(dto.gameId())
                .orElseThrow(() -> new IllegalArgumentException("Game not found (there is no game with this ID )"));
        //validate team size
        if (dto.teamSize() > game.getPlayers()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Team size cannot exceed max players of the game ")); // add the max players for this game here
        }
        //creating post
        Post post = Post.builder()
                .title(dto.title())
                .description(dto.description())
                .teamSize(dto.teamSize())
                .currentPlayers(0)
                .owner(owner)
                .game(game)
                .createdAt(LocalDateTime.now())
                .active(true)
                .build();

        postRepository.save(post);

        return ResponseEntity.ok(Map.of("message", "Post created successfully", "post", mapToDto(post)));

    }

    public ResponseEntity<?> getAllPosts() {
        List<PostDto> posts = postRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();

        return ResponseEntity.ok(Map.of("posts", posts));
    }

    public ResponseEntity<?> getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found , no post with this Id"));
        return ResponseEntity.ok(Map.of("post", mapToDto(post)));
    }

    @Transactional
    public ResponseEntity<?> deletePost(Long id, Long userId) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isOwner = post.getOwner().getId().equals(userId);
        boolean isAdmin = user.getRole().name().equals("ADMIN");

        if (!isOwner && !isAdmin) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "You are not allowed to delete this post"));
        }

        postRepository.delete(post);
        return ResponseEntity.ok(Map.of("message", "Post deleted successfully"));
    }

    // Mapper
    private PostDto mapToDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getTeamSize(),
                post.getCurrentPlayers(),
                UserDto.from(post.getOwner()),
                post.getGame(),
                post.getCreatedAt(),
                post.getActive()
        );
    }

}
