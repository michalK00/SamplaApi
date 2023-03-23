package com.sampla.samplaapi.service;

import com.sampla.samplaapi.dto.create.CreateResearchDto;
import com.sampla.samplaapi.dto.update.UpdateResearchDto;
import com.sampla.samplaapi.entity.Research;
import com.sampla.samplaapi.repository.ResearchRepository;
import com.sampla.samplaapi.dto.brief.ResearchBriefDto;
import com.sampla.samplaapi.dto.base.ResearchDto;
import com.sampla.samplaapi.rest.exceptions.ResearchDeletionException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResearchService {
    private final Mappings mappings;
    private final ResearchRepository researchRepository;


    public ResearchService(Mappings mappings, ResearchRepository researchRepository) {
        this.mappings = mappings;
        this.researchRepository = researchRepository;
    }

    public Optional<ResearchDto> getResearchById(Long id){
        return researchRepository.findById(id)
                .map(mappings::mapEntityToNormal);
    }
    public Page<ResearchBriefDto> getAllResearchesBrief(Pageable paging){
        return researchRepository.findAll(paging)
                .map(mappings::mapEntityToBrief);
    }
    public List<ResearchBriefDto> getAllResearchesBrief(){
        return researchRepository.findAll()
                .stream()
                .map(mappings::mapEntityToBrief)
                .toList();
    }
    public Page<ResearchBriefDto> getAllResearchesBriefWhereStatusIs(String label, Pageable paging){
        Research.Status status = Research.Status.valueOfLabel(label);
        return researchRepository.findAllByStatus(status, paging)
                .map(mappings::mapEntityToBrief);
    }

    public ResearchBriefDto createResearch(@Valid CreateResearchDto createResearchDto) {
        Research research = mappings.mapCreateToEntity(createResearchDto);
        Research savedResearch = researchRepository.save(research);
        return mappings.mapEntityToBrief(savedResearch);
    }

    public void updateResearch(@Valid UpdateResearchDto dto, Long researchId) {
        Research research = mappings.mapUpdateToEntity(dto, researchId);
        researchRepository.save(research);
    }

    public void deleteResearch(Long researchId){
        ResearchDto researchDto = this.getResearchById(researchId).orElseThrow();
        if (!researchDto.getSampleList().isEmpty()) {
            throw new ResearchDeletionException("Cannot delete research with samples");
        }
        researchRepository.deleteById(researchId);
    }
}
