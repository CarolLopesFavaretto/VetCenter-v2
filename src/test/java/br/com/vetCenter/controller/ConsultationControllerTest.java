package br.com.vetCenter.controller;

import br.com.vetCenter.VetCenterV2Application;
import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.config.TestContainerConfig;
import br.com.vetCenter.data.VetCenterData;
import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.domain.entity.Consultation;
import br.com.vetCenter.framework.adapter.in.dtos.request.ConsultationRequest;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = VetCenterV2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestContainerConfig.class)
@RunWith(SpringRunner.class)
public class ConsultationControllerTest {

    @Autowired
    private GuardianService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreatedConsultationByGuardian() throws Exception {

        GuardianRequest guardian = VetCenterData.guardian();

        Animal animal = VetCenterData.getAnimal();

        Consultation consultation = VetCenterData.getConsultation();

        guardian.setAnimals(new ArrayList<>());
        guardian.getAnimals().add(animal);

        GuardianResponse guardianResponse = service.create(guardian);


        mvc.perform(MockMvcRequestBuilders.post(
                                "/consultation/guardian/{guardianId}/animal/{animalId}", guardianResponse.getId(), animal.getId())
                        .content(objectMapper.writeValueAsString(consultation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateConsultationByGuardian() throws Exception {

        GuardianRequest guardian = VetCenterData.guardian();

        Animal animal = VetCenterData.getAnimal();

        Consultation consultation = VetCenterData.getConsultation();

        animal.setConsultations(new ArrayList<>());
        animal.getConsultations().add(consultation);

        guardian.setAnimals(new ArrayList<>());
        guardian.getAnimals().add(animal);

        GuardianResponse guardianResponse = service.create(guardian);

        ConsultationRequest requestUpdate = ConsultationRequest.builder()
                .nameVeterinary("Maria")
                .cause("vomito")
                .observations("mudanca de racao para complemento")
                .date(LocalDate.now())
                .value(50.0)
                .regress(true)
                .build();


        mvc.perform(MockMvcRequestBuilders
                        .put("/consultation/guardian/{guardianId}/animal/" +
                                        "{animalId}/consultations/{consultationId}",
                                guardianResponse.getId(), animal.getId(), consultation.getId())
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
                    assertThat(animalUp.getName()).isEqualTo(animal.getName());
                    assertThat(animalUp.getAge()).isEqualTo(animal.getAge());
                    assertThat(animalUp.getRace()).isEqualTo(animal.getRace());
                    assertThat(animalUp.getType()).isEqualTo(animal.getType());

                    Consultation consultationUp = animalUp.getConsultations().get(0);
                    assertThat(consultationUp.getNameVeterinary()).isEqualTo(requestUpdate.getNameVeterinary());
                    assertThat(consultationUp.getCause()).isEqualTo(requestUpdate.getCause());
                    assertThat(consultationUp.getObservations()).isEqualTo(requestUpdate.getObservations());
                    assertThat(consultationUp.getDate()).isEqualTo(requestUpdate.getDate());
                    assertThat(consultationUp.getValue()).isEqualTo(requestUpdate.getValue());
                    assertThat(consultationUp.getRegress()).isEqualTo(requestUpdate.getRegress());
                });
    }

    @Test
    public void shouldDeletedConsultationByGuardian() throws Exception{

        GuardianRequest guardian = VetCenterData.guardian();

        Animal animal = VetCenterData.getAnimal();

        Consultation consultation = VetCenterData.getConsultation();

        animal.setConsultations(new ArrayList<>());
        animal.getConsultations().add(consultation);

        guardian.setAnimals(new ArrayList<>());
        guardian.getAnimals().add(animal);

        GuardianResponse guardianResponse = service.create(guardian);


        mvc.perform(MockMvcRequestBuilders
                        .delete("/consultation/guardian/{guardianId}/animal/" +
                                        "{animalId}/consultations/{consultationId}",
                                guardianResponse.getId(), animal.getId(), consultation.getId())
                        .content(objectMapper.writeValueAsString(consultation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
