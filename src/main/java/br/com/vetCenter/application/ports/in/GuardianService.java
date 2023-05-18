package br.com.vetCenter.application.ports.in;

import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;

import java.util.List;
import java.util.Optional;

public interface GuardianService {

    GuardianResponse create(GuardianRequest request);

    List<GuardianResponse> findAll();

    Optional<GuardianResponse> findById(String id);

    GuardianResponse update(String id, GuardianRequest request);

    void deleteById(String id);
}
