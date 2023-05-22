package br.com.vetCenter.framework.controller;

import br.com.vetCenter.application.ports.in.AnimalService;
import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
