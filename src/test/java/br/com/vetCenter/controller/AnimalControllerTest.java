package br.com.vetCenter.controller;

import br.com.vetCenter.VetCenterV2Application;
import br.com.vetCenter.application.ports.in.AnimalService;
import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.config.TestContainerConfig;
import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = VetCenterV2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestContainerConfig.class)
@RunWith(SpringRunner.class)
public class AnimalControllerTest {

    @Autowired
    private GuardianService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreatedAnimalByGuardian() throws Exception {

        GuardianRequest guardian = GuardianRequest.builder()
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();

        GuardianResponse guardianResponse = service.create(guardian);

        AnimalRequest animal = AnimalRequest.builder()
                .name("Mimi")
                .age(2)
                .race("sem ração identificada")
                .type("canino")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/animal/{guardianId}", guardianResponse.getId())
                        .content(objectMapper.writeValueAsString(animal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateAnimalByGuardian() throws Exception {

        GuardianRequest guardian = GuardianRequest.builder()
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();

        Animal animal = Animal.builder()
                .id("123456987")
                .name("Mimi")
                .age(2)
                .race("sem ração identificada")
                .type("canino")
                .build();

        guardian.setAnimals(new ArrayList<>());
        guardian.getAnimals().add(animal);

        GuardianResponse guardianResponse = service.create(guardian);

        AnimalRequest requestUpdate = AnimalRequest.builder()
                .name("Lolo")
                .age(5)
                .race("vira lata")
                .type("canino")
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .put("/animal/{guardianId}/animals/{animalId}", guardianResponse.getId(), animal.getId())
                        .content(objectMapper.writeValueAsString(requestUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    GuardianResponse resp = objectMapper.readValue(contentAsString, GuardianResponse.class);
                    assertThat(resp.getName()).isEqualTo(guardian.getName());
                    assertThat(resp.getCpf()).isEqualTo(guardian.getCpf());
                    assertThat(resp.getTelephone()).isEqualTo(guardian.getTelephone());

                    Animal animalUp = resp.getAnimals().get(0);
                    assertThat(animalUp.getName()).isEqualTo(requestUpdate.getName());
                    assertThat(animalUp.getAge()).isEqualTo(requestUpdate.getAge());
                    assertThat(animalUp.getRace()).isEqualTo(requestUpdate.getRace());
                    assertThat(animalUp.getType()).isEqualTo(requestUpdate.getType());
                });
    }

    @Test
    public void shouldDeletedAnimalByGuardian() throws Exception{

        GuardianRequest guardian = GuardianRequest.builder()
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();

        Animal animal = Animal.builder()
                .id("123456987")
                .name("Mimi")
                .age(2)
                .race("sem ração identificada")
                .type("canino")
                .build();

        guardian.setAnimals(new ArrayList<>());
        guardian.getAnimals().add(animal);

        GuardianResponse guardianResponse = service.create(guardian);

        AnimalRequest requestUpdate = AnimalRequest.builder()
                .name("Lolo")
                .age(5)
                .race("vira lata")
                .type("canino")
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .delete("/animal/{guardianId}/animals/{animalId}", guardianResponse.getId(), animal.getId())
                        .content(objectMapper.writeValueAsString(requestUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

}

