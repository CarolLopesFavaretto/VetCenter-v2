package br.com.vetCenter.framework.adapter.in.dtos.mappers;

import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.AnimalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AnimalMapper {

    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    AnimalResponse toModel(Animal animal);

    Animal toObject(AnimalRequest request);

    Animal toObjectGuardianId(String guardianId, AnimalRequest request);

    AnimalResponse toObj (AnimalRequest request);
}
