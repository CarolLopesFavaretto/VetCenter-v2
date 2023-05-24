package br.com.vetCenter.application.ports.in;

import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.http.ResponseEntity;

public interface AnimalService {

    GuardianResponse create(String guardianId, AnimalRequest request);

    GuardianResponse update(String guardianId, String animalId, AnimalRequest request);

    ResponseEntity<Void> deleteById(String guardianId, String animalId);

}
