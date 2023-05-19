package br.com.vetCenter.controller;

import br.com.vetCenter.VetCenterV2Application;
import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.config.TestContainerConfig;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = VetCenterV2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestContainerConfig.class)
@RunWith(SpringRunner.class)
public class GuardianController {


    @Autowired
    private GuardianService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreatedGuardian() throws Exception {
        GuardianRequest guardian = GuardianRequest.builder()
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();

        service.create(guardian);

        mvc.perform(MockMvcRequestBuilders.post("/guardian")
                        .content(objectMapper.writeValueAsString(guardian))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindAllGuardians() throws Exception {
        GuardianRequest guardian = GuardianRequest.builder()
                .name("Amanda")
                .cpf("41246532874")
                .telephone(1140543186L)
                .name("Camila")
                .cpf("41246532874")
                .telephone(1140543186L)
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .get("/guardian")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    List<GuardianResponse> resp = objectMapper.readValue(contentAsString, new TypeReference<List<GuardianResponse>>() {
                    });
                    assertThat(resp.size()).isEqualTo(2);

                    for (GuardianResponse guardianList : resp) {
                        assertThat(guardianList.getName()).isEqualTo(guardian.getName());
                        assertThat(guardianList.getCpf()).isEqualTo(guardian.getCpf());
                        assertThat(guardianList.getTelephone()).isEqualTo(guardian.getTelephone());
                    }
                });
    }

    @Test
    public void shouldFindById() throws Exception {
        GuardianRequest guardian = GuardianRequest.builder()
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();
        GuardianResponse guardianResponse = service.create(guardian);


        mvc.perform(MockMvcRequestBuilders
                        .get("/guardian/{id}", guardianResponse.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    GuardianResponse resp = objectMapper.readValue(contentAsString, GuardianResponse.class);
                    assertThat(resp.getName()).isEqualTo(guardian.getName());
                    assertThat(resp.getCpf()).isEqualTo(guardian.getCpf());
                    assertThat(resp.getTelephone()).isEqualTo(guardian.getTelephone());
                });
    }

    @Test
    public void shouldUpdateGuardian() throws Exception {

        GuardianRequest guardian = GuardianRequest.builder()
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();

        GuardianResponse guardianResponse = service.create(guardian);

        mvc.perform(MockMvcRequestBuilders
                        .put("/guardian/{id}", guardianResponse.getId())
                        .content(objectMapper.writeValueAsString(guardian))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    GuardianResponse resp = objectMapper.readValue(contentAsString, GuardianResponse.class);
                    assertThat(resp.getName()).isEqualTo(guardian.getName());
                    assertThat(resp.getCpf()).isEqualTo(guardian.getCpf());
                    assertThat(resp.getTelephone()).isEqualTo(guardian.getTelephone());
                });
    }

    @Test
    public void shouldDeleteGuardian() throws Exception {
        GuardianRequest guardian = GuardianRequest.builder()
                .name("Carol")
                .cpf("41246532874")
                .telephone(1140543186L)
                .build();

        GuardianResponse guardianResponse = service.create(guardian);

        mvc.perform(MockMvcRequestBuilders.delete("/guardian/{id}", guardianResponse.getId())
                        .content(objectMapper.writeValueAsString(guardian))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}
