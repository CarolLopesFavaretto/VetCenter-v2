package br.com.vetCenter.controller;

import br.com.vetCenter.VetCenterV2Application;
import br.com.vetCenter.application.ports.in.AnimalService;
import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.config.TestContainerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = VetCenterV2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestContainerConfig.class)
@RunWith(SpringRunner.class)
public class AnimalControllerTest {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private GuardianService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreatedGuardian() throws Exception {

    }
}
