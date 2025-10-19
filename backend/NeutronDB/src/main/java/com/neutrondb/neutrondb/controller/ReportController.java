package com.neutrondb.neutrondb.controller;

import com.neutrondb.neutrondb.domain.dto.ReportDTO;
import com.neutrondb.neutrondb.domain.entities.ReportEntity;
import com.neutrondb.neutrondb.mapper.Mapper;
import com.neutrondb.neutrondb.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/reports")
@RestController
public class ReportController {

    private final ReportService reportService;

    private final Mapper<ReportEntity, ReportDTO> reportMapper;

    public ReportController(ReportService reportService, Mapper<ReportEntity, ReportDTO> reportMapper) {
        this.reportService = reportService;
        this.reportMapper = reportMapper;
    }

    @GetMapping
    public List<ReportDTO> getReports() {
        List<ReportEntity> reports = reportService.getReports();
        return reports.stream()
                .map(reportMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{reportId}")
    public ReportDTO getReportById(@PathVariable int reportId) {
        ReportEntity reportEntity = reportService.getReportById(reportId);
        return reportMapper.mapTo(reportEntity);
    }

    @PostMapping
    public ReportDTO addReport(@RequestBody ReportDTO reportDTO) {
        ReportEntity reportEntity = reportMapper.mapFrom(reportDTO);
        reportService.addReport(reportEntity);
        return reportMapper.mapTo(reportEntity);
    }

    @PutMapping("/{reportId}")
    public ReportDTO updateReport(@PathVariable int reportId, @RequestBody ReportDTO reportDTO) {
        ReportEntity reportEntity = reportMapper.mapFrom(reportDTO);
        reportService.updateReport(reportEntity);
        return reportMapper.mapTo(reportEntity);
    }

    @DeleteMapping("/{reportId}")
    public void deleteReport(@PathVariable int reportId) {
        ReportEntity reportEntity = reportService.getReportById(reportId);
        reportService.deleteReport(reportEntity);
    }

    @DeleteMapping
    public void deleteAllReports() {
        reportService.deleteAllReports();
    }
}