package br.com.vetCenter.framework.controller;

import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guardian")
public class GuardianController {

    @Autowired
    private GuardianService service;

    @PostMapping
    public GuardianResponse created(@RequestBody GuardianRequest request) {
        return service.create(request);
    }
}
