package com.sampla.samplaapi.service;

import com.sampla.samplaapi.dto.base.ResearchDto;
import com.sampla.samplaapi.dto.base.SampleDto;
import com.sampla.samplaapi.dto.brief.SampleBriefDto;
import com.sampla.samplaapi.dto.create.CreateResearchDto;
import com.sampla.samplaapi.dto.brief.ResearchBriefDto;
import com.sampla.samplaapi.dto.create.CreateSampleDto;
import com.sampla.samplaapi.dto.update.UpdateResearchDto;
import com.sampla.samplaapi.dto.update.UpdateSampleDto;
import com.sampla.samplaapi.entity.Research;
import com.sampla.samplaapi.entity.Sample;
import com.sampla.samplaapi.repository.ResearchRepository;
import com.sampla.samplaapi.repository.SampleRepository;
import com.sampla.samplaapi.service.time.LocalDateService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class Mappings {

    LocalDateService localDateService;
    ResearchRepository researchRepository;
    SampleRepository sampleRepository;

    public Mappings(LocalDateService localDateService, ResearchRepository researchRepository, SampleRepository sampleRepository) {
        this.localDateService = localDateService;
        this.researchRepository = researchRepository;
        this.sampleRepository = sampleRepository;
    }

    public Sample mapCreateToEntity(CreateSampleDto dto, Long researchId) {
        return Sample.builder()
                .sampleCode(dto.getSampleCode())
                .research(researchRepository.findById(researchId).orElseThrow())
                .created(localDateService.now())
                .updated(localDateService.now())
                .build();
    }
    public Sample mapUpdateToEntity(@Valid UpdateSampleDto dto, Long entityId){
        Sample entity = sampleRepository.findById(entityId).orElseThrow();
        return Sample.builder()
                .id(entity.getId())
                .sampleCode(dto.getSampleCode())
                .storage(dto.getStorage())
                .storageType(dto.getStorageType())
                .solvent(dto.getSolvent())
                .dilution(dto.getDilution())
                .material(dto.getMaterial())
                .analysisMethod(dto.getAnalysisMethod())
                .research(entity.getResearch())
                .created(entity.getCreated())
                .updated(localDateService.now())
                .build();
    }

    public Research mapCreateToEntity(@Valid CreateResearchDto dto){
        return Research.builder()
                .name(dto.getName())
                .status(Research.Status.IN_PROGRESS)
                .created(localDateService.now())
                .updated(localDateService.now())
                .build();
    }
    public Research mapUpdateToEntity(@Valid UpdateResearchDto dto, Long entityId){
        Research entity = researchRepository.findById(entityId).orElseThrow();
        return Research.builder()
                .id(entity.getId())
                .name(dto.getName())
                .customer(dto.getCustomer())
                .researchDescription(dto.getResearchDescription())
                .sampleList(entity.getSampleList())
                .status(dto.getStatus())
                .created(entity.getCreated())
                .updated(localDateService.now())
                .build();
    }
    public SampleBriefDto mapEntityToBrief(@Valid Sample sample){
        return SampleBriefDto.builder()
                .id(sample.getId())
                .sampleCode(sample.getSampleCode())
                .researchName(sample.getResearch().getName())
                .researchId(sample.getResearch().getId())
                .created(sample.getCreated())
                .updated(sample.getUpdated())
                .build();
    }

    public SampleDto mapEntityToNormal(@Valid Sample sample){
        return SampleDto.builder()
                .id(sample.getId())
                .sampleCode(sample.getSampleCode())
                .storage(sample.getStorage())
                .storageType(sample.getStorageType())
                .solvent(sample.getSolvent())
                .dilution(sample.getDilution())
                .material(sample.getMaterial())
                .analysisMethod(sample.getAnalysisMethod())
                .researchId(sample.getResearch().getId())
                .researchName(sample.getResearch().getName())
                .created(sample.getCreated())
                .updated(sample.getUpdated())
                .build();
    }


    public ResearchBriefDto mapEntityToBrief(@Valid Research research){
        return ResearchBriefDto.builder()
                .id(research.getId())
                .name(research.getName())
                .status(research.getStatus())
                .created(research.getCreated())
                .build();
    }

    public ResearchDto mapEntityToNormal(@Valid Research research){
        return ResearchDto.builder()
                .id(research.getId())
                .name(research.getName())
                .customer(research.getCustomer())
                .researchDescription(research.getResearchDescription())
                .sampleList(research.getSampleList().stream()
                        .map(this::mapEntityToBrief)
                        .toList())
                .status(research.getStatus())
                .created(research.getCreated())
                .updated(research.getUpdated())
                .build();
    }
}
