package br.com.vetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {

    private String id;
    private String name;
    private Integer age;
    private String type;
    private String race;
    private List<Consultation> consultations;
}
