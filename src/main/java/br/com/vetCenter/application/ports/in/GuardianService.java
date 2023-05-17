package br.com.vetCenter.application.ports.in;

import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;

import java.util.List;
import java.util.Optional;

public interface GuardianService {

    GuardianResponse create(GuardianRequest request);

    List<GuardianResponse> findAll();

    Optional<GuardianResponse> findById(Long id);

    GuardianResponse update(Long id, GuardianRequest request);

    void deleteById(Long id);
}
