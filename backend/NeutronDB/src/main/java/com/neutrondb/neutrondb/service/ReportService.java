package com.neutrondb.neutrondb.service;

import com.neutrondb.neutrondb.domain.entities.ReportEntity;
import com.neutrondb.neutrondb.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public List<ReportEntity> getReports() {
        return reportRepository.findAll();
    }

    public ReportEntity getReportById(int reportId) {
        return reportRepository.findById(reportId).orElse(new ReportEntity());
    }

    public void addReport(ReportEntity reportEntity) {
        reportRepository.saveAndFlush(reportEntity);
    }

    public void updateReport(ReportEntity reportEntity) {
        reportRepository.save(reportEntity);
    }

    public void deleteReport(ReportEntity reportEntity) {
        reportRepository.delete(reportEntity);
    }

    public void deleteAllReports() {
        reportRepository.deleteAll();
    }
}
