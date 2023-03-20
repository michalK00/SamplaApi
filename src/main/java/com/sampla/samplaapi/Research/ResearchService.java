package com.sampla.samplaapi.Research;

import com.sampla.samplaapi.Research.ResearchDto.ResearchDto;
import com.sampla.samplaapi.Research.ResearchDto.ResearchDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

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
    public List<ResearchDto> getAllResearches(){
        return researchRepository.findAll()
                .stream()
                .map(researchDtoMapper::map)
                .toList();
    }
    public Page<ResearchDto> getAllResearches(Pageable paging){
        return researchRepository.findAll(paging)
                .map(researchDtoMapper::map);
    }
    public Page<ResearchDto> getAllResearchesWhereStatusIs(String label ,Pageable paging){
        Research.Status status = Research.Status.valueOfLabel(label);
        return researchRepository.findAllByStatus(status, paging)
                .map(researchDtoMapper::map);
    }

    public ResearchDto saveResearch(ResearchDto dto) {
        Research research = researchDtoMapper.map(dto);
        Research savedResearch = researchRepository.save(research);
        return researchDtoMapper.map(savedResearch);
    }
}
