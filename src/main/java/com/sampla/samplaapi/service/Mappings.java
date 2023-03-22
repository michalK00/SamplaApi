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
import com.sampla.samplaapi.service.time.LocalDateService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class Mappings {

    LocalDateService localDateService;
    ResearchRepository researchRepository;

    public Mappings(LocalDateService localDateService, ResearchRepository researchRepository) {
        this.localDateService = localDateService;
        this.researchRepository = researchRepository;
    }

    public Sample map(CreateSampleDto dto, Long researchId) {
        return Sample.builder()
                .sampleCode(dto.getSampleCode())
                .research(researchRepository.findById(researchId).orElseThrow())
                .created(localDateService.now())
                .updated(localDateService.now())
                .build();
    }
    public Sample map(@Valid UpdateSampleDto dto){
        return Sample.builder()
                .sampleCode(dto.getSampleCode())
                .storage(dto.getStorage())
                .storageType(dto.getStorageType())
                .solvent(dto.getSolvent())
                .dilution(dto.getDilution())
                .material(dto.getMaterial())
                .analysisMethod(dto.getAnalysisMethod())
                .build();
    }

    public Research map(@Valid CreateResearchDto dto){
        return Research.builder()
                .name(dto.getName())
                .status(Research.Status.IN_PROGRESS)
                .created(localDateService.now())
                .updated(localDateService.now())
                .build();
    }
    public Research map(@Valid UpdateResearchDto dto){
        return Research.builder()
                .name(dto.getName())
                .customer(dto.getCustomer())
                .researchDescription(dto.getResearchDescription())
                .status(dto.getStatus())
                .build();
    }
    public SampleBriefDto mapToBrief(@Valid Sample sample){
        return SampleBriefDto.builder()
                .id(sample.getId())
                .sampleCode(sample.getSampleCode())
                .researchName(sample.getResearch().getName())
                .researchId(sample.getResearch().getId())
                .created(sample.getCreated())
                .updated(sample.getUpdated())
                .build();
    }

    public SampleDto mapToNormal(@Valid Sample sample){
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


    public ResearchBriefDto mapToBrief(@Valid Research research){
        return ResearchBriefDto.builder()
                .id(research.getId())
                .name(research.getName())
                .status(research.getStatus())
                .created(research.getCreated())
                .build();
    }

    public ResearchDto mapToNormal(@Valid Research research){
        return ResearchDto.builder()
                .id(research.getId())
                .name(research.getName())
                .customer(research.getCustomer())
                .researchDescription(research.getResearchDescription())
                .sampleList(research.getSampleList().stream()
                        .map(this::mapToBrief)
                        .toList())
                .status(research.getStatus())
                .created(research.getCreated())
                .updated(research.getUpdated())
                .build();
    }


}
