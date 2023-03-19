package com.sampla.samplaapi.Sample;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

}
