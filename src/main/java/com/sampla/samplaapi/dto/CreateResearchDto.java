package com.sampla.samplaapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResearchDto {
    @NotBlank(message = "Research has to have a name")
    private String name;

}
