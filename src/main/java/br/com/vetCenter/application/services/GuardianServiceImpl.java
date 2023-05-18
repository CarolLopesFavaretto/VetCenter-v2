package br.com.vetCenter.application.services;

import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.framework.adapter.out.persistence.GuardianRepository;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.GuardianMapper;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardianServiceImpl implements GuardianService {

    @Autowired
    private GuardianRepository repository;

    @Autowired
    private GuardianMapper mapper;


    @Override
    public GuardianResponse create(GuardianRequest request) {
        Guardian entity = mapper.toObject(request);
        Guardian saved = repository.save(entity);
        return mapper.toModel(saved);
    }

    @Override
    public List<GuardianResponse> findAll() {
        return null;

    }

    @Override
    public Optional<GuardianResponse> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public GuardianResponse update(Long id, GuardianRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
