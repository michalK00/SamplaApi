package com.sampla.samplaapi.Sample.SampleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class SampleDto {


    private Long id;
    private String sampleCode;
    private double storage;
    private String storageType;
    private String solvent;
    private double dilution;
    private String material;
    private String analysisMethod;
    private Long researchId;
    private String researchName;
    private LocalDate created;
    private LocalDate updated;


}
