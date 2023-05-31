package br.com.vetCenter.framework.controller;

import br.com.vetCenter.application.ports.in.PrescriptionService;
import br.com.vetCenter.framework.adapter.in.dtos.request.PrescriptionRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService service;

    @PostMapping("guardian/{guardianId}/animal/{animalId}/consultations/{consultationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianResponse createdPrescription(@PathVariable("guardianId") String guardianId,
                                                @PathVariable String animalId,
                                                @PathVariable String consultationId,
                                                @RequestBody PrescriptionRequest request) {

        return service.create(guardianId, animalId, consultationId, request);
    }

    @PutMapping("guardian/{guardianId}/animal/{animalId}/consultations/{consultationId}/prescriptions/{prescriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public GuardianResponse updatedPrescription(@PathVariable("guardianId") String guardianId,
                                                @PathVariable String animalId,
                                                @PathVariable String consultationId,
                                                @PathVariable String prescriptionId,
                                                @RequestBody PrescriptionRequest request) {
        return service.update(guardianId, animalId, consultationId, prescriptionId, request);
    }

    @DeleteMapping("guardian/{guardianId}/animal/{animalId}/consultations/{consultationId}/prescriptions/{prescriptionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletedPrescription(@PathVariable("guardianId") String guardianId,
                                                    @PathVariable String animalId,
                                                    @PathVariable String consultationId,
                                                    @PathVariable String prescriptionId) {
        return service.deleteById(guardianId, animalId, consultationId, prescriptionId);
    }
}
