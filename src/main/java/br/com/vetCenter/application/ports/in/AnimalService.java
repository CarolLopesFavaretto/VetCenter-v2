package br.com.vetCenter.application.ports.in;

import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.AnimalResponse;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    GuardianResponse create(String guardianId, AnimalRequest request);

    List<AnimalResponse> findAll();

    Optional<AnimalResponse> findById(String id);

    Optional<AnimalResponse> update(String id, AnimalRequest request);

    ResponseEntity<Void> deleteById(String id);


}
