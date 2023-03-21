package com.sampla.samplaapi.research;

import com.sampla.samplaapi.research.researchDto.ResearchBriefDto;
import com.sampla.samplaapi.research.researchDto.ResearchBriefDtoMapper;
import com.sampla.samplaapi.research.researchDto.ResearchDto;
import com.sampla.samplaapi.research.researchDto.ResearchDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResearchService {

    private final ResearchRepository researchRepository;
    private final ResearchDtoMapper researchDtoMapper;
    private final ResearchBriefDtoMapper researchBriefDtoMapper;

    public ResearchService(ResearchRepository researchRepository,
                           ResearchDtoMapper researchDtoMapper,
                           ResearchBriefDtoMapper researchBriefDtoMapper) {
        this.researchRepository = researchRepository;
        this.researchDtoMapper = researchDtoMapper;
        this.researchBriefDtoMapper = researchBriefDtoMapper;
    }

    Optional<ResearchDto> getResearchById(Long id){
        return researchRepository.findById(id)
                .map(researchDtoMapper::map);
    }
    Page<ResearchBriefDto> getAllResearchesBrief(Pageable paging){
        return researchRepository.findAll(paging)
                .map(researchBriefDtoMapper::map);
    }
    List<ResearchBriefDto> getAllResearchesBrief(){
        return researchRepository.findAll()
                .stream()
                .map(researchBriefDtoMapper::map)
                .toList();
    }
    Page<ResearchBriefDto> getAllResearchesBriefWhereStatusIs(String label ,Pageable paging){
        Research.Status status = Research.Status.valueOfLabel(label);
        return researchRepository.findAllByStatus(status, paging)
                .map(researchBriefDtoMapper::map);
    }

    ResearchBriefDto saveResearchBrief(ResearchBriefDto dto) {
        Research research = researchBriefDtoMapper.map(dto);
        Research savedResearch = researchRepository.save(research);
        return researchBriefDtoMapper.map(savedResearch);
    }

    void updateResearch(ResearchDto dto){
        Research research = researchDtoMapper.map(dto);
        researchRepository.save(research);
    }

    void deleteResearch(Long researchId){
        researchRepository.deleteById(researchId);
    }
}
