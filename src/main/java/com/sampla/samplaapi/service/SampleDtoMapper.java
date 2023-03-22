package com.sampla.samplaapi.service;

import com.sampla.samplaapi.dto.base.SampleDto;
import com.sampla.samplaapi.repository.ResearchRepository;
import com.sampla.samplaapi.entity.Sample;
import org.springframework.stereotype.Service;

@Service
public class SampleDtoMapper {
    private final ResearchRepository researchRepository;

    public SampleDtoMapper(ResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }

    public SampleDto map(Sample sample){
        return SampleDto.builder()
                .id(sample.getId())
                .sampleCode(sample.getSampleCode())
                .storage(sample.getStorage())
                .storageType(sample.getStorageType())
                .solvent(sample.getSolvent())
                .dilution(sample.getDilution())
                .material(sample.getMaterial())
                .analysisMethod(sample.getAnalysisMethod())
                .researchId(sample.getResearch().getId())
                .researchName(sample.getResearch().getName())
                .created(sample.getCreated())
                .updated(sample.getUpdated())
                .build();
    }
    public Sample map(SampleDto dto){
        return Sample.builder()
                .id(dto.getId())
                .sampleCode(dto.getSampleCode())
                .storage(dto.getStorage())
                .storageType(dto.getStorageType())
                .solvent(dto.getSolvent())
                .dilution(dto.getDilution())
                .material(dto.getMaterial())
                .analysisMethod(dto.getAnalysisMethod())
                .research(researchRepository.findById(dto.getResearchId()).orElseThrow())
                .updated(dto.getUpdated())
                .created(dto.getCreated())
                .build();
    }

}
