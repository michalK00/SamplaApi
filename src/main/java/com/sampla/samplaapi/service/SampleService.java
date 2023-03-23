package com.sampla.samplaapi.service;

import com.sampla.samplaapi.dto.create.CreateSampleDto;
import com.sampla.samplaapi.dto.update.UpdateSampleDto;
import com.sampla.samplaapi.entity.Sample;
import com.sampla.samplaapi.repository.SampleRepository;
import com.sampla.samplaapi.dto.brief.SampleBriefDto;
import com.sampla.samplaapi.dto.base.SampleDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    private final SampleRepository sampleRepository;
    private final Mappings mappings;

    public SampleService(SampleRepository sampleRepository, Mappings mappings) {
        this.sampleRepository = sampleRepository;
        this.mappings = mappings;
    }

    public Optional<SampleDto> getSampleById(Long id){
        return sampleRepository.findById(id).map(mappings::mapEntityToNormal);
    }

    public SampleDto createSample(@Valid CreateSampleDto dto, Long researchId){
        Sample sample = mappings.mapCreateToEntity(dto, researchId);
        Sample savedSample = sampleRepository.save(sample);
        return mappings.mapEntityToNormal(savedSample);
    }

    public void updateSample(@Valid UpdateSampleDto updateSampleDto, Long sampleId) {
        Sample sample = mappings.mapUpdateToEntity(updateSampleDto, sampleId);
        sampleRepository.save(sample);
    }

    public void deleteSample(Long id) {
        sampleRepository.deleteById(id);
    }
    public Page<SampleBriefDto> getSampleBriefs(Pageable paging, Long researchId){
        return sampleRepository.findAllByResearch_Id(researchId, paging).map(mappings::mapEntityToBrief);
    }
    public List<SampleBriefDto> getSampleBriefs(Long researchId){
        return sampleRepository.findAllByResearch_Id(researchId).stream().map(mappings::mapEntityToBrief).toList();
    }
    @Transactional
    public void deleteAllSamples(Long researchId) {
        sampleRepository.deleteAllByResearch_Id(researchId);
    }
}
