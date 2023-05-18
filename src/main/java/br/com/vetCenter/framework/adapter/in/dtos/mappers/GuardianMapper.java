package br.com.vetCenter.framework.adapter.in.dtos.mappers;

import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface GuardianMapper {

    GuardianMapper INSTANCE = Mappers.getMapper(GuardianMapper.class);

    GuardianResponse toModel(Guardian guardian);

    Guardian toObject(GuardianRequest request);

    List<GuardianResponse> toCollectionModel(List<Guardian> guardians);


}
