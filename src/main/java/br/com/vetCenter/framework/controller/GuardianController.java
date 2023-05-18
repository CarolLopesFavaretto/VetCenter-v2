package br.com.vetCenter.framework.controller;

import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guardian")
public class GuardianController {

    @Autowired
    private GuardianService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianResponse created(@RequestBody GuardianRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<GuardianResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GuardianResponse> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleted(@PathVariable String id) {
        service.deleteById(id);
    }


}
