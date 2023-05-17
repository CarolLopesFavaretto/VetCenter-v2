package br.com.vetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Animal {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String type;
    private String race;
    private Guardian guardian;
    private List<Consultation> consultations;
}
