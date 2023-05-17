package br.com.vetCenter.framework.adapter.in.dtos.mappers;

import br.com.vetCenter.domain.entity.Prescription;
import br.com.vetCenter.framework.adapter.in.dtos.request.PrescriptionRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.PrescriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PrescriptionMapper {

    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

    PrescriptionResponse toModel(Prescription prescription);

    Prescription toObject(PrescriptionRequest request);
}
