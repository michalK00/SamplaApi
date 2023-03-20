package com.sampla.samplaapi.Sample;

import com.sampla.samplaapi.Sample.SampleDto.SampleBriefDtoMapper;
import com.sampla.samplaapi.Sample.SampleDto.SampleDto;
import com.sampla.samplaapi.Sample.SampleDto.SampleDtoMapper;
import org.springframework.stereotype.Service;
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

    Optional<SampleDto> getSampleById(Long id){
        return sampleRepository.findById(id).map(sampleDtoMapper::map);
    }

    SampleDto saveSample(SampleDto dto){
        Sample sample = sampleDtoMapper.map(dto);
        Sample savedSample = sampleRepository.save(sample);
        return sampleDtoMapper.map(savedSample);
    }

     void updateSample(SampleDto sampleDto) {
        Sample jobOffer = sampleDtoMapper.map(sampleDto);
        sampleRepository.save(jobOffer);
    }
//    public Page<SampleBriefDto> getSamples(Pageable paging, Long researchId){
//        return sampleRepository.findAllByResearch_Id(paging, researchId).map(sampleBriefDtoMapper::map);
//    }
}
