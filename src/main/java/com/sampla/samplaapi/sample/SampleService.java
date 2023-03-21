package com.sampla.samplaapi.sample;

import com.sampla.samplaapi.sample.sampleDto.SampleBriefDto;
import com.sampla.samplaapi.sample.sampleDto.SampleBriefDtoMapper;
import com.sampla.samplaapi.sample.sampleDto.SampleDto;
import com.sampla.samplaapi.sample.sampleDto.SampleDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    private final SampleRepository sampleRepository;
    private final SampleDtoMapper sampleDtoMapper;
    private final SampleBriefDtoMapper sampleBriefDtoMapper;

    SampleService(SampleRepository sampleRepository, SampleDtoMapper sampleDtoMapper, SampleBriefDtoMapper sampleBriefDtoMapper) {
        this.sampleRepository = sampleRepository;
        this.sampleDtoMapper = sampleDtoMapper;
        this.sampleBriefDtoMapper = sampleBriefDtoMapper;
    }

    Optional<SampleDto> getSampleById(Long id){
        return sampleRepository.findById(id).map(sampleDtoMapper::map);
    }

    SampleDto saveSample(SampleDto dto){
        Sample sample = sampleDtoMapper.map(dto);
        Sample savedSample = sampleRepository.save(sample);
        return sampleDtoMapper.map(savedSample);
    }

     void updateSample(SampleDto sampleDto) {
        Sample jobOffer = sampleDtoMapper.map(sampleDto);
        sampleRepository.save(jobOffer);
    }

    public void deleteSample(Long id) {
        sampleRepository.deleteById(id);
    }
    public Page<SampleBriefDto> getSampleBriefs(Pageable paging, Long researchId){
        return sampleRepository.findAllByResearch_Id(researchId, paging).map(sampleBriefDtoMapper::map);
    }
    public List<SampleBriefDto> getSampleBriefs(Long researchId){
        return sampleRepository.findAllByResearch_Id(researchId).stream().map(sampleBriefDtoMapper::map).toList();
    }
}
