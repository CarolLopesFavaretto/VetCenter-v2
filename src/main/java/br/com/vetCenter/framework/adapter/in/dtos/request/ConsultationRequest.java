package br.com.vetCenter.framework.adapter.in.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultationRequest {

    private String nameVeterinary;
    private Double value;
    private String cause;
    private String observations;
    private LocalDate date;
    private Boolean regress;
}
