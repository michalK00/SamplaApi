package com.sampla.samplaapi.research.researchDto;

import com.sampla.samplaapi.research.Research;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ResearchBriefDto {

    private Long id;
    @NotBlank(message = "Research has to have a name")
    private String name;
    private Research.Status status;
    @PastOrPresent(message = "Created date can't be in the future")
    private LocalDate created;

}
