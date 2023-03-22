package com.sampla.samplaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String customer;
    private String researchDescription;
    @OneToMany(mappedBy = "research")
    @JsonIgnore
    private List<Sample> sampleList;
    @Enumerated(EnumType.STRING)
    private Status status;
    @PastOrPresent
    private LocalDate created;
    @PastOrPresent
    private LocalDate updated;


    public enum Status{
        COMPLETED("Completed"), IN_PROGRESS("In Progress");

        public final String label;

         Status(String label) {
            this.label = label;
        }

        public static Status valueOfLabel(String label) {
            for (Status s : values()) {
                if (s.label.equals(label)) {
                    return s;
                }
            }
            return null;
        }
    }
}
