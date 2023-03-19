package com.sampla.samplaapi.Sample;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SampleService {

    private final SampleRepository sampleRepository;

    public SampleService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public Optional<Sample> findSampleById(Long id){
        return sampleRepository.findById(id);
    }
}
