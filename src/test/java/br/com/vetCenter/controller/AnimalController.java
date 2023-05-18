package br.com.vetCenter.controller;

import br.com.vetCenter.VetCenterV2Application;
import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.framework.adapter.out.persistence.GuardianRepository;
import br.com.vetCenter.config.TestContainerConfig;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = VetCenterV2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestContainerConfig.class)
@RunWith(SpringRunner.class)
public class AnimalController {


    @Autowired
    private GuardianService service;

    @Autowired
    private GuardianRepository repository;

    @Test
    public void test(){
        service.create(GuardianRequest.builder().name("Carol").cpf("145123853").telephone(11523641L)
                .build());
        List<Guardian> all = repository.findAll();
    }
}
