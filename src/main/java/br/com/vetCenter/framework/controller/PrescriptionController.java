package br.com.vetCenter.framework.controller;

import br.com.vetCenter.application.ports.in.PrescriptionService;
import br.com.vetCenter.framework.adapter.in.dtos.request.PrescriptionRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
