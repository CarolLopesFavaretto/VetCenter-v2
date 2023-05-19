package br.com.vetCenter.application.services;

import br.com.vetCenter.application.ports.in.GuardianService;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.GuardianMapper;
import br.com.vetCenter.framework.adapter.in.dtos.request.GuardianRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import br.com.vetCenter.framework.adapter.out.persistence.GuardianRepository;
import br.com.vetCenter.framework.exceptions.ResourceNotFoundException;
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
        List<Guardian> guardians = repository.findAll();
        return mapper.toCollectionModel(guardians);
    }

    @Override
    public Optional<GuardianResponse> findById(String id) {
        return repository.findById(id).map(guardian -> (mapper.toModel(guardian)));
    }

    @Override
    public Optional<GuardianResponse> update(String id, GuardianRequest request) throws ResourceNotFoundException {
        return repository.findById(id).map(guardian -> {
            guardian.setCpf(request.getCpf());
            guardian.setName(request.getName());
            guardian.setTelephone(request.getTelephone());
            Guardian guardianUpdate = repository.save(guardian);
            return Optional.of(mapper.toModel(guardianUpdate));
        }).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
