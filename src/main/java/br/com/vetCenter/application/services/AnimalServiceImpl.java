package br.com.vetCenter.application.services;

import br.com.vetCenter.application.ports.in.AnimalService;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.AnimalMapper;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.GuardianMapper;
import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.AnimalResponse;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import br.com.vetCenter.framework.adapter.out.persistence.GuardianRepository;
import br.com.vetCenter.framework.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private GuardianRepository repository;

    @Autowired
    private AnimalMapper mapper;

    @Autowired
    private GuardianMapper guardianMapper;


    @Override
    public GuardianResponse create(String guardianId, AnimalRequest request) {
        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            if (guardian.getAnimals() == null) {
                guardian.setAnimals(new ArrayList<>());
            }
            guardian.getAnimals().add(mapper.toObject(request));
            return guardianMapper.toModel(repository.save(guardian));
        }
        throw new ResourceNotFoundException("Guardian invalido");
    }

    @Override
    public List<AnimalResponse> findAll() {
        return null;
    }

    @Override
    public Optional<AnimalResponse> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<AnimalResponse> update(String id, AnimalRequest request) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        return null;
    }
}
