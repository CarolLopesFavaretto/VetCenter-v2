package br.com.vetCenter.application.ports.in;

import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GuardianService {

    GuardianResponse create(GuardianRequest request);

    List<GuardianResponse> findAll();

    Optional<GuardianResponse> findById(String id);

    Optional<GuardianResponse> update(String id, GuardianRequest request);

    ResponseEntity<Void> deleteById(String id);
}
