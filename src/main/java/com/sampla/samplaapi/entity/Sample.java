package com.sampla.samplaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Sample has to have a name")
    private String sampleCode;
    @PositiveOrZero(message = "Storage must be non-negative")
    private double storage;
    @Enumerated(EnumType.STRING)
    private StorageType storageType;
    private String solvent;
    @PositiveOrZero(message = "Dilution must be non-negative")
    private double dilution;
    private String material;
    private String analysisMethod;
    @ManyToOne(optional = false)
    @JoinColumn(name = "research_id")
    @JsonIgnore
    private Research research;
    @PastOrPresent(message = "Created date can't be in the future")
    private LocalDate created;
    @PastOrPresent(message = "Updated date can't be in the future")
    private LocalDate updated;

    public enum StorageType{
        MICROGRAMS("μg"), MILLIGRAMS("mg"), GRAMS("g"), MICROLITERS("μl"), MILLILITERS("ml");

        public final String label;

        StorageType(String label) {
            this.label = label;
        }

    }
}
