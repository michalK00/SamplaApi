package com.sampla.samplaapi.Research.ResearchDto;

import com.sampla.samplaapi.Research.Research;
import com.sampla.samplaapi.Sample.SampleDto.SampleBriefDtoMapper;
import com.sampla.samplaapi.Sample.SampleDto.SampleDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class ResearchDtoMapper {
    private final SampleBriefDtoMapper sampleBriefDtoMapper;

    public ResearchDtoMapper(SampleBriefDtoMapper sampleBriefDtoMapper) {
        this.sampleBriefDtoMapper = sampleBriefDtoMapper;
    }

    public ResearchDto map(Research research){
        return new ResearchDto(
                research.getId(),
                research.getName(),
                research.getCustomer(),
                research.getResearchDescription(),
                research.getSampleList().stream()
                        .map(sampleBriefDtoMapper::map)
                        .toList(),
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

        research.setSampleList(dto.getSampleList().stream()
                        .map(sampleBriefDtoMapper::map)
                        .toList());
        research.setStatus(Research.Status.valueOfLabel(dto.getStatus()));
        research.setCreated(dto.getCreated());
        research.setUpdated(dto.getUpdated());
        return research;
    }

}
