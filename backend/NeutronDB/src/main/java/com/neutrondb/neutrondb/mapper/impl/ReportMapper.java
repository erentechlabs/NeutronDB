package com.neutrondb.neutrondb.mapper.impl;

import com.neutrondb.neutrondb.domain.dto.ReportDTO;
import com.neutrondb.neutrondb.domain.entities.ReportEntity;
import com.neutrondb.neutrondb.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper implements Mapper<ReportEntity, ReportDTO> {

    private final ModelMapper modelMapper;

    public ReportMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public ReportDTO mapTo(ReportEntity reportEntity) {
        return modelMapper.map(reportEntity, ReportDTO.class);
    }

    @Override
    public ReportEntity mapFrom(ReportDTO reportDTO) {
        return modelMapper.map(reportDTO, ReportEntity.class);

    }
}