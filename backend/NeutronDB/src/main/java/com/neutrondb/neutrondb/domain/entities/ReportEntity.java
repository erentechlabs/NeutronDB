package com.neutrondb.neutrondb.domain.entities;

import com.neutrondb.neutrondb.domain.enums.Instability;
import com.neutrondb.neutrondb.domain.enums.Verdict;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reports")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GameEntity game;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(name = "verdict")
    private Verdict verdict;

    @Enumerated(EnumType.STRING)
    @Column(name = "instability")
    private Instability instability;

    @Column(name = "multiplayer_rating")
    private String multiplayerRating;

    @Column(name = "overall_rating")
    private String overallRating;

    @Column(name = "distro")
    private String distro;

    @Column(name = "kernel")
    private String kernel;

    @Column(name = "ram_gb")
    private int ramGb;

    @Column(name = "gpu_driver")
    private String gpuDriver;

    @Column(name = "gpu_model")
    private String gpuModel;

    @Column(name = "cpu_model")
    private String cpuModel;
}