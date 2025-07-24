package com.neutrondb.neutrondb.domain.entities;

import com.neutrondb.neutrondb.domain.enums.Platform;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "developer")
    private String developer;

    @Column(name = "genre")
    private String genre;

    @ElementCollection(targetClass = Platform.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "game_platforms", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Set<Platform> platforms = new HashSet<>();

    @Column(name = "deck_verified_status")
    private Boolean deckVerifiedStatus;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ReportEntity> reports = new ArrayList<>();
}