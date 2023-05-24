package br.com.vetCenter.application.services;

import br.com.vetCenter.application.ports.in.ConsultationService;
import br.com.vetCenter.framework.adapter.in.dtos.request.ConsultationRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    @Override
    public GuardianResponse create(String guardianId, String animalId, ConsultationRequest request) {
        return null;
    }

    @Override
    public GuardianResponse update(String guardianId, String animalId, String consultationId, ConsultationRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteById(String guardianId, String animalId, String consultationId) {
        return null;
    }
}
