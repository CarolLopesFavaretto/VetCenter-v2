package br.com.vetCenter.application.ports.in;

import br.com.vetCenter.framework.adapter.in.dtos.request.PrescriptionRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.http.ResponseEntity;

public interface PrescriptionService {

    GuardianResponse create(String guardianId, String animalId, String consultationId, PrescriptionRequest request);

    GuardianResponse update(String guardianId, String animalId, String consultationId, String prescriptionId,
                            PrescriptionRequest request);

    ResponseEntity<Void> deleteById(String guardianId, String animalId, String consultationId, String prescriptionId);
}
