package com.neutrondb.neutrondb.domain.dto;

import com.neutrondb.neutrondb.domain.enums.Instability;
import com.neutrondb.neutrondb.domain.enums.Verdict;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Integer id;
    private Integer gameId;
    private String body;
    private Verdict verdict;
    private Instability instability;
    private String multiplayerRating;
    private String overallRating;
    private String distro;
    private String kernel;
    private int ramGb;
    private String gpuDriver;
    private String gpuModel;
    private String cpuModel;
}