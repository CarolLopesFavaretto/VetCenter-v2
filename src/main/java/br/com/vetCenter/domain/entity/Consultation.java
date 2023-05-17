package br.com.vetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {

    @Id
    private Long id;
    private String nameVeterinary;
    private Double value;
    private String cause;
    private String observations;
    private LocalDate date;
    private Boolean regress;
    private List<Prescription> prescriptions;
}
