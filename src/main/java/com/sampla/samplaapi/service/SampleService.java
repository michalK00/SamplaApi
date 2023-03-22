package com.sampla.samplaapi.service;

import com.sampla.samplaapi.entity.Sample;
import com.sampla.samplaapi.repository.SampleRepository;
import com.sampla.samplaapi.dto.SampleBriefDto;
import com.sampla.samplaapi.dto.SampleDto;
import jakarta.validation.Valid;
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

    public Optional<SampleDto> getSampleById(Long id){
        return sampleRepository.findById(id).map(sampleDtoMapper::map);
    }

    public SampleDto saveSample(@Valid SampleDto dto){
        Sample sample = sampleDtoMapper.map(dto);
        Sample savedSample = sampleRepository.save(sample);
        return sampleDtoMapper.map(savedSample);
    }

    public void updateSample(@Valid SampleDto sampleDto) {
        Sample sample = sampleDtoMapper.map(sampleDto);
        sampleRepository.save(sample);
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

    public void deleteAllSamples(Long researchId) {
        sampleRepository.deleteAllByResearch_Id(researchId);
    }
}
