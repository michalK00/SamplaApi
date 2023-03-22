package com.sampla.samplaapi.service;

import com.sampla.samplaapi.dto.SampleBriefDto;
import com.sampla.samplaapi.repository.ResearchRepository;
import com.sampla.samplaapi.entity.Sample;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class SampleBriefDtoMapper {

    private final ResearchRepository researchRepository;

    public SampleBriefDtoMapper(ResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }

    public SampleBriefDto map(@Valid Sample sample){
        return SampleBriefDto.builder()
                .id(sample.getId())
                .sampleCode(sample.getSampleCode())
                .researchId(sample.getResearch().getId())
                .researchName(sample.getResearch().getName())
                .created(sample.getCreated())
                .updated(sample.getUpdated())
                .build();
    }
    public Sample map(@Valid SampleBriefDto dto){
        return Sample.builder()
                .id(dto.getId())
                .sampleCode(dto.getSampleCode())
                .research(researchRepository.findById(dto.getResearchId()).orElseThrow())
                .updated(dto.getUpdated())
                .created(dto.getCreated())
                .build();
    }

}
