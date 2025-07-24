package com.neutrondb.neutrondb.repository;

import com.neutrondb.neutrondb.domain.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
}
