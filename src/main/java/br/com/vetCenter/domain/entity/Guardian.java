package br.com.vetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "guardians")
public class Guardian {

    @Id
    @Field(value = "_id")
    private String id;
    private String name;
    private String cpf;
    private Long telephone;
    private List<Animal> animals;
}
