package com.sampla.samplaapi.sample.sampleDto;

import com.sampla.samplaapi.sample.Sample;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SampleDto {

    private Long id;
    @NotBlank(message = "Sample has to have a name")
    private String sampleCode;
    @PositiveOrZero(message = "Storage must be non-negative")
    private double storage;
    private Sample.StorageType storageType;
    private String solvent;
    @PositiveOrZero(message = "Dilution must be non-negative")
    private double dilution;
    private String material;
    private String analysisMethod;
    private Long researchId;
    private String researchName;
    @PastOrPresent(message = "Created date can't be in the future")
    private LocalDate created;
    @PastOrPresent(message = "Updated date can't be in the future")
    private LocalDate updated;


}
