package com.sampla.samplaapi.Sample;

import com.sampla.samplaapi.Sample.SampleDto.SampleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }


    @GetMapping("/researches/{researchId}/{sampleId}")
    public ResponseEntity<SampleDto> getSample(@PathVariable Long researchId, @PathVariable Long sampleId){
        return sampleService.findSampleById(sampleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/researches/{researchId}")
    public ResponseEntity<SampleDto> addSample(@PathVariable Long researchId, @RequestBody SampleDto sample){
        sample.setResearchId(researchId);
        SampleDto savedSample = sampleService.saveSample(sample);
        URI savedSampleUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedSample.getId())
                .toUri();
        return ResponseEntity.created(savedSampleUri).body(savedSample);
    }
}
