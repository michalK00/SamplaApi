package com.sampla.samplaapi.Research.ResearchDto;

import com.sampla.samplaapi.Sample.Sample;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ResearchDto {

    private Long id;
    private String name;
    private String customer;
    private String researchDescription;
    private List<Sample> sampleList;
    private String status;
    private LocalDate created;
    private LocalDate updated;



}
