package com.sampla.samplaapi.research.researchDto;

import com.sampla.samplaapi.research.Research;
import org.springframework.stereotype.Service;

@Service
public class ResearchBriefDtoMapper {
    public ResearchBriefDto map(Research research){
        return new ResearchBriefDto(research.getId(), research.getName(), research.getStatus().label, research.getCreated());
    }
    public Research map(ResearchBriefDto dto){
        Research research = new Research();
        research.setId(dto.getId());
        research.setName(dto.getName());
        research.setStatus(Research.Status.valueOfLabel(dto.getStatus()));
        research.setCreated(dto.getCreated());
        return research;
    }
}
