package com.sampla.samplaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SampleBriefDto {

    private Long id;
    @NotBlank(message = "Sample has to have a name")
    private String sampleCode;
    private Long researchId;
    private String researchName;
    @PastOrPresent(message = "Created date can't be in the future")
    private LocalDate created;
    @PastOrPresent(message = "Updated date can't be in the future")
    private LocalDate updated;


}
