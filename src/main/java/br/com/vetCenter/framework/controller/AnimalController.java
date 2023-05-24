package br.com.vetCenter.framework.controller;

import br.com.vetCenter.application.ports.in.AnimalService;
import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/{guardianId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianResponse created(@PathVariable("guardianId") String guardianId, @RequestBody AnimalRequest request) {
        return animalService.create(guardianId, request);
    }

    @PutMapping("/{guardianId}/animals/{animalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianResponse updated(@PathVariable("guardianId") String guardianId,
                                    @PathVariable("animalId") String animalId, @RequestBody AnimalRequest request) {
        return animalService.update(guardianId, animalId, request);
    }

    @DeleteMapping("/{guardianId}/animals/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAnimal(@PathVariable("guardianId") String guardianId,
                                             @PathVariable("animalId") String animalId) {
        return animalService.deleteById(guardianId, animalId);
    }

}
