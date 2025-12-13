package com.zanta.lfp.post.dto;

import com.zanta.lfp.game.model.Game;
import com.zanta.lfp.user.Dto.UserDto;
import java.time.LocalDateTime;

// api response
public record PostDto (


        Long id ,
        String title,
        String description,
        int teamSize ,
        int currentPlayers,
        UserDto owner,
        Game game ,
        LocalDateTime CreatedAt,
        Boolean active
)
{}



