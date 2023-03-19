package com.sampla.samplaapi.Sample.SampleDto;

import com.sampla.samplaapi.Research.Research;
import com.sampla.samplaapi.Research.ResearchRepository;
import com.sampla.samplaapi.Sample.Sample;
import org.springframework.stereotype.Service;

import static com.sampla.samplaapi.Sample.Sample.StorageType;

@Service
public class SampleDtoMapper {
    private final ResearchRepository researchRepository;

    public SampleDtoMapper(ResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }

    public SampleDto map(Sample sample){
        return new SampleDto(
                sample.getId(),
                sample.getSampleCode(),
                sample.getStorage(),
                sample.getStorageType().label,
                sample.getSolvent(),
                sample.getDilution(),
                sample.getMaterial(),
                sample.getAnalysisMethod(),
                sample.getResearch().getId(),
                sample.getResearch().getName(),
                sample.getCreated(),
                sample.getUpdated()
        );
    }
    public Sample map(SampleDto dto){
        Sample sample = new Sample();
        sample.setId(dto.getId());
        sample.setSampleCode(dto.getSampleCode());
        sample.setStorage(dto.getStorage());
        sample.setStorageType(StorageType.valueOf(dto.getStorageType()));
        sample.setSolvent(dto.getSolvent());
        sample.setDilution(dto.getDilution());
        sample.setMaterial(dto.getMaterial());
        sample.setMaterial(dto.getMaterial());
        sample.setAnalysisMethod(dto.getAnalysisMethod());
        Research research = researchRepository.findById(dto.getResearchId()).orElseThrow();
        sample.setResearch(research);
        return sample;
    }

}
