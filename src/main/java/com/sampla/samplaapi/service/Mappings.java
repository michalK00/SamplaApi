package com.sampla.samplaapi.service;

import com.sampla.samplaapi.dto.CreateResearchDto;
import com.sampla.samplaapi.dto.ResearchBriefDto;
import com.sampla.samplaapi.entity.Research;
import com.sampla.samplaapi.service.time.LocalDateService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class Mappings {

    LocalDateService localDateService;

    public Mappings(LocalDateService localDateService) {
        this.localDateService = localDateService;
    }

    public Research map(@Valid CreateResearchDto dto){
        return Research.builder()
                .name(dto.getName())
                .status(Research.Status.IN_PROGRESS)
                .created(localDateService.now())
                .updated(localDateService.now())
                .build();
    }

    public ResearchBriefDto map(@Valid Research research){
        return ResearchBriefDto.builder()
                .id(research.getId())
                .name(research.getName())
                .status(research.getStatus())
                .created(research.getCreated())
                .build();
    }

}
