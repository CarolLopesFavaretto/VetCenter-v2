package br.com.vetCenter.framework.adapter.in.dtos.mappers;

import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.AnimalResponse;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AnimalMapper {

    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    AnimalResponse toModel(Animal animal);

    Animal toObject(AnimalRequest request);

    List<AnimalResponse> toCollectionModel(List<Guardian> guardians);

    AnimalResponse toObj (AnimalRequest request);
}
