package com.sampla.samplaapi.Sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sampla.samplaapi.Research.Research;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sampleCode;
    private double storage;
    @Enumerated(EnumType.STRING)
    private StorageType storageType;
    private String solvent;
    private double dilution;
    private String material;
    private String analysisMethod;
    @ManyToOne(optional = false)
    @JoinColumn(name = "research_id")
    @JsonIgnore
    private Research research;
    private LocalDate created;
    private LocalDate updated;

    public enum StorageType{
        MICROGRAMS("μg"), MILLIGRAMS("mg"), GRAMS("g"), MICROLITERS("μl"), MILLILITERS("ml");

        public final String label;

        StorageType(String label) {
            this.label = label;
        }

        public static StorageType valueOfLabel(String label) {
            for (StorageType s : values()) {
                if (s.label.equals(label)) {
                    return s;
                }
            }
            return null;
        }
    }
}
