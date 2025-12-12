package com.zanta.lfp.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Game name cannot be blank")
    @Size(max = 100, message = "Game name must be less than 100 characters")
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @NotNull(message = "Players number cannot be null")
    @Min(value = 1, message = "Players must be at least 1")
    @Column(name = "players", nullable = false)
    private Integer players;

    @NotBlank(message = "Picture url cannot be blank")
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @NotNull(message = "Modes cannot be null")
    @ElementCollection
    @CollectionTable(name = "game_modes", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "mode", nullable = false)
    private List<String> modes;
}

