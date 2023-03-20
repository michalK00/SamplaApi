package com.sampla.samplaapi.Research;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sampla.samplaapi.Sample.Sample;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String customer;
    private String researchDescription;
    @OneToMany(mappedBy = "research")
    @JsonIgnore
    private List<Sample> sampleList;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate created;
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
