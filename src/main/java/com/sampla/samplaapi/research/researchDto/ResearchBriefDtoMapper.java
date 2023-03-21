package com.sampla.samplaapi.research.researchDto;

import com.sampla.samplaapi.research.Research;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ResearchBriefDtoMapper {
    public ResearchBriefDto map(@Valid Research research){
        return ResearchBriefDto.builder()
                .id(research.getId())
                .name(research.getName())
                .status(research.getStatus())
                .created(research.getCreated())
                .build();
    }
    public Research map(@Valid ResearchBriefDto dto){
        return Research.builder()
                .id(dto.getId())
                .name(dto.getName())
                .status(dto.getStatus())
                .created(dto.getCreated())
                .build();
    }
}
