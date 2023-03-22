package com.sampla.samplaapi.service;

import com.sampla.samplaapi.dto.create.CreateResearchDto;
import com.sampla.samplaapi.entity.Research;
import com.sampla.samplaapi.repository.ResearchRepository;
import com.sampla.samplaapi.dto.brief.ResearchBriefDto;
import com.sampla.samplaapi.dto.base.ResearchDto;
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
    private final ResearchDtoMapper researchDtoMapper;
    private final ResearchBriefDtoMapper researchBriefDtoMapper;

    public ResearchService(Mappings mappings, ResearchRepository researchRepository, ResearchDtoMapper researchDtoMapper, ResearchBriefDtoMapper researchBriefDtoMapper) {
        this.mappings = mappings;
        this.researchRepository = researchRepository;
        this.researchDtoMapper = researchDtoMapper;
        this.researchBriefDtoMapper = researchBriefDtoMapper;
    }

    public Optional<ResearchDto> getResearchById(Long id){
        return researchRepository.findById(id)
                .map(researchDtoMapper::map);
    }
    public Page<ResearchBriefDto> getAllResearchesBrief(Pageable paging){
        return researchRepository.findAll(paging)
                .map(researchBriefDtoMapper::map);
    }
    public List<ResearchBriefDto> getAllResearchesBrief(){
        return researchRepository.findAll()
                .stream()
                .map(researchBriefDtoMapper::map)
                .toList();
    }
    public Page<ResearchBriefDto> getAllResearchesBriefWhereStatusIs(String label ,Pageable paging){
        Research.Status status = Research.Status.valueOfLabel(label);
        return researchRepository.findAllByStatus(status, paging)
                .map(researchBriefDtoMapper::map);
    }

    public ResearchBriefDto createResearch(@Valid CreateResearchDto dto) {
        Research research = mappings.map(dto);
        Research savedResearch = researchRepository.save(research);
        return researchBriefDtoMapper.map(savedResearch);
    }

    public void updateResearch(@Valid ResearchDto dto){
        Research research = researchDtoMapper.map(dto);
        researchRepository.save(research);
    }

    public void deleteResearch(Long researchId){
        researchRepository.deleteById(researchId);
    }
}
