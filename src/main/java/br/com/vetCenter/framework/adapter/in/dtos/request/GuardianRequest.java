package br.com.vetCenter.framework.adapter.in.dtos.request;

import br.com.vetCenter.domain.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardianRequest {

    private String name;
    private String cpf;
    private Long telephone;

}
