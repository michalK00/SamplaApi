package com.sampla.samplaapi.dto.update;

import com.sampla.samplaapi.entity.Research;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResearchDto {

    @NotBlank(message = "Research has to have a name")
    private String name;
    private String customer;
    private String researchDescription;
    private Research.Status status;

}
