package com.sampla.samplaapi.Sample;

import com.sampla.samplaapi.Sample.SampleDto.SampleDto;
import com.sampla.samplaapi.Sample.SampleDto.SampleDtoMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SampleService {

    private final SampleRepository sampleRepository;
    private final SampleDtoMapper sampleDtoMapper;

    public SampleService(SampleRepository sampleRepository, SampleDtoMapper sampleDtoMapper) {
        this.sampleRepository = sampleRepository;
        this.sampleDtoMapper = sampleDtoMapper;
    }

    public Optional<SampleDto> findSampleById(Long id){
        return sampleRepository.findById(id).map(sampleDtoMapper::map);
    }

    public SampleDto saveSample(SampleDto dto){
        Sample sample = sampleDtoMapper.map(dto);
        Sample savedSample = sampleRepository.save(sample);
        return sampleDtoMapper.map(savedSample);
    }
}
