package com.sampla.samplaapi.Sample.SampleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class SampleBriefDto {

    private Long id;
    private String sampleCode;
    private Long researchId;
    private String researchName;
    private LocalDate created;
    private LocalDate updated;


}
