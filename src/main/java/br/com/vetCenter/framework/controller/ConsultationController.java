package br.com.vetCenter.framework.controller;

import br.com.vetCenter.application.ports.in.ConsultationService;
import br.com.vetCenter.framework.adapter.in.dtos.request.ConsultationRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService service;

    @PostMapping("guardian/{guardianId}/animal/{animalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianResponse created(@PathVariable("guardianId") String guardianId,
                                    @PathVariable String animalId, @RequestBody ConsultationRequest request) {
        return service.create(guardianId, animalId, request);
    }

    @PutMapping("guardian/{guardianId}/animal/{animalId}/consultations/{consultationId}")
    @ResponseStatus(HttpStatus.OK)
    public GuardianResponse created(@PathVariable("guardianId") String guardianId,
                                    @PathVariable String animalId,
                                    @PathVariable String consultationId,
                                    @RequestBody ConsultationRequest request) {
        return service.update(guardianId, animalId, consultationId, request);
    }

    @DeleteMapping("guardian/{guardianId}/animal/{animalId}/consultations/{consultationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleted(@PathVariable("guardianId") String guardianId,
                                        @PathVariable String animalId,
                                        @PathVariable String consultationId) {
        return service.deleteById(guardianId, animalId, consultationId);
    }
}
