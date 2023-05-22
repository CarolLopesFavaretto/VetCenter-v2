package br.com.vetCenter.framework.adapter.in.dtos.response;

import br.com.vetCenter.domain.entity.Consultation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalResponse {

    private String id;
    private String name;
    private Integer age;
    private String type;
    private String race;
    private List<Consultation> consultations;
}
