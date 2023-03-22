package com.sampla.samplaapi.dto.create;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class CreateResearchDto {
    @NotBlank(message = "Research has to have a name")
    private String name;

}
