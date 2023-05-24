package br.com.vetCenter.application.ports.in;

import br.com.vetCenter.framework.adapter.in.dtos.request.ConsultationRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.http.ResponseEntity;

public interface ConsultationService {

    GuardianResponse create(String guardianId, String animalId, ConsultationRequest request);

    GuardianResponse update(String guardianId, String animalId, String consultationId, ConsultationRequest request);

    ResponseEntity<Void> deleteById(String guardianId, String animalId, String consultationId);
}
