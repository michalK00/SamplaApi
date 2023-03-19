package com.sampla.samplaapi.Research.ResearchDto;

import com.sampla.samplaapi.Research.Research;
import org.springframework.stereotype.Service;

@Service
public class ResearchDtoMapper {
    public ResearchDto map(Research research){
        return new ResearchDto(
                research.getId(),
                research.getName(),
                research.getCustomer(),
                research.getResearchDescription(),
                research.getSampleList(),
                research.getStatus().label,
                research.getCreated(),
                research.getUpdated()
        );
    }
    public Research map(ResearchDto dto){
        Research research = new Research();
        research.setId(dto.getId());
        research.setName(dto.getName());
        research.setCustomer(dto.getCustomer());
        research.setResearchDescription(dto.getResearchDescription());
        research.setSampleList(dto.getSampleList());
        research.setStatus(Research.Status.valueOfLabel(dto.getStatus()));
        research.setCreated(dto.getCreated());
        research.setUpdated(dto.getUpdated());
        return research;
    }

}
