package com.sampla.samplaapi.research.researchDto;

import com.sampla.samplaapi.research.Research;
import com.sampla.samplaapi.sample.sampleDto.SampleBriefDtoMapper;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ResearchDtoMapper {
    private final SampleBriefDtoMapper sampleBriefDtoMapper;

    public ResearchDtoMapper(SampleBriefDtoMapper sampleBriefDtoMapper) {
        this.sampleBriefDtoMapper = sampleBriefDtoMapper;
    }

    public ResearchDto map(@Valid Research research){
        return ResearchDto.builder()
                .id(research.getId())
                .name(research.getName())
                .customer(research.getCustomer())
                .researchDescription(research.getResearchDescription())
                .sampleList(research.getSampleList().stream()
                        .map(sampleBriefDtoMapper::map)
                        .toList())
                .status(research.getStatus())
                .created(research.getCreated())
                .updated(research.getUpdated())
                .build();
    }
    public Research map(@Valid ResearchDto dto){
        return Research.builder()
                .id(dto.getId())
                .name(dto.getName())
                .customer(dto.getCustomer())
                .researchDescription(dto.getResearchDescription())
                .sampleList(dto.getSampleList().stream()
                        .map(sampleBriefDtoMapper::map)
                        .toList())
                .status(dto.getStatus())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .build();
    }

}
