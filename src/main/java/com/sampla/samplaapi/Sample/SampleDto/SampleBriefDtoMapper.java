package com.sampla.samplaapi.Sample.SampleDto;

import com.sampla.samplaapi.Research.Research;
import com.sampla.samplaapi.Research.ResearchRepository;
import com.sampla.samplaapi.Sample.Sample;
import org.springframework.stereotype.Service;

@Service
public class SampleBriefDtoMapper {

    private final ResearchRepository researchRepository;

    public SampleBriefDtoMapper(ResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }

    public SampleBriefDto map(Sample sample){
        return new SampleBriefDto(
                sample.getId(),
                sample.getSampleCode(),
                sample.getResearch().getId(),
                sample.getResearch().getName(),
                sample.getCreated(),
                sample.getUpdated()
        );
    }
    public Sample map(SampleBriefDto dto){
        Sample sample = new Sample();
        sample.setId(dto.getId());
        sample.setSampleCode(dto.getSampleCode());
        Research research = researchRepository.findById(dto.getResearchId()).orElseThrow();
        sample.setResearch(research);
        sample.setUpdated(dto.getUpdated());
        sample.setCreated(dto.getCreated());
        return sample;
    }

}
