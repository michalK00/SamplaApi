package com.sampla.samplaapi.research.researchDto;

import com.sampla.samplaapi.research.Research;
import com.sampla.samplaapi.sample.sampleDto.SampleBriefDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResearchDto {

    private Long id;
    @NotBlank(message = "Research has to have a name")
    private String name;
    private String customer;
    private String researchDescription;
    private List<SampleBriefDto> sampleList;
    private Research.Status status;
    @PastOrPresent(message = "Created date can't be in the future")
    private LocalDate created;
    @PastOrPresent(message = "Updated date can't be in the future")
    private LocalDate updated;



}
