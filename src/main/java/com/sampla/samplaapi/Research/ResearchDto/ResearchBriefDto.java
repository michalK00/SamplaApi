package com.sampla.samplaapi.Research.ResearchDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ResearchBriefDto {

    private Long id;
    private String name;
    private String status;
    private LocalDate created;

}
