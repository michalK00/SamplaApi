package com.sampla.samplaapi.dto.create;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class CreateSampleDto {

    @NotBlank(message = "Sample has to have a name")
    private String sampleCode;

}
