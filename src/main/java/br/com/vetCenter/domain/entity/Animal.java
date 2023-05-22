package br.com.vetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    private String id;
    private String name;
    private Integer age;
    private String type;
    private String race;
    private List<Consultation> consultations;
}
