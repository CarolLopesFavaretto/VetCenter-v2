package br.com.vetCenter.framework.adapter.in.dtos.mappers;

import br.com.vetCenter.domain.entity.Consultation;
import br.com.vetCenter.framework.adapter.in.dtos.request.ConsultationRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.ConsultationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ConsultationMapper {

    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);

    ConsultationResponse toModel(Consultation consultation);

    Consultation toObject(ConsultationRequest request);
}
