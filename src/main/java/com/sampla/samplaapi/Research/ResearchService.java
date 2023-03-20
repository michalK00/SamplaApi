package com.sampla.samplaapi.Research;

import com.sampla.samplaapi.Research.ResearchDto.ResearchBriefDto;
import com.sampla.samplaapi.Research.ResearchDto.ResearchBriefDtoMapper;
import com.sampla.samplaapi.Research.ResearchDto.ResearchDto;
import com.sampla.samplaapi.Research.ResearchDto.ResearchDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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

    public Optional<ResearchDto> getResearchById(Long id){
        return researchRepository.findById(id)
                .map(researchDtoMapper::map);
    }
    public Page<ResearchBriefDto> getAllResearchesBrief(Pageable paging){
        return researchRepository.findAll(paging)
                .map(researchBriefDtoMapper::map);
    }
    public Page<ResearchBriefDto> getAllResearchesBriefWhereStatusIs(String label ,Pageable paging){
        Research.Status status = Research.Status.valueOfLabel(label);
        return researchRepository.findAllByStatus(status, paging)
                .map(researchBriefDtoMapper::map);
    }

    public ResearchBriefDto saveResearchBrief(ResearchBriefDto dto) {
        Research research = researchBriefDtoMapper.map(dto);
        Research savedResearch = researchRepository.save(research);
        return researchBriefDtoMapper.map(savedResearch);
    }
}
