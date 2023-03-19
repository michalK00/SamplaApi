package com.sampla.samplaapi.Research;

import com.sampla.samplaapi.Research.ResearchDto.ResearchDto;
import com.sampla.samplaapi.Research.ResearchDto.ResearchDtoMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResearchService {

    private final ResearchRepository researchRepository;
    private final ResearchDtoMapper researchDtoMapper;

    public ResearchService(ResearchRepository researchRepository, ResearchDtoMapper researchDtoMapper) {
        this.researchRepository = researchRepository;
        this.researchDtoMapper = researchDtoMapper;
    }

    public Optional<ResearchDto> getResearchById(Long id){
        return researchRepository.findById(id)
                .map(researchDtoMapper::map);
    }

    public ResearchDto saveResearch(ResearchDto dto) {
        Research research = researchDtoMapper.map(dto);
        Research savedResearch = researchRepository.save(research);
        return researchDtoMapper.map(savedResearch);
    }
}
