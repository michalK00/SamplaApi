package com.sampla.samplaapi.dto.update;

import com.sampla.samplaapi.entity.Sample;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateSampleDto {
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

}
