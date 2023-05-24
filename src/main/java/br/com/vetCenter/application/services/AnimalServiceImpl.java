package br.com.vetCenter.application.services;

import br.com.vetCenter.application.ports.in.AnimalService;
import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.AnimalMapper;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.GuardianMapper;
import br.com.vetCenter.framework.adapter.in.dtos.request.AnimalRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import br.com.vetCenter.framework.adapter.out.persistence.GuardianRepository;
import br.com.vetCenter.framework.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

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
            Animal animal = mapper.toObject(request);
            animal.setId(UUID.randomUUID().toString());
            guardian.getAnimals().add(animal);
            return guardianMapper.toModel(repository.save(guardian));
        }
        throw new ResourceNotFoundException("Guardian invalido");
    }

    @Override
    public GuardianResponse update(String guardianId, String animalId, AnimalRequest request) {
        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            Optional<Animal> optAnimal = guardian.getAnimals().stream()
                    .filter(animal -> animalId.equals(animal.getId())).findFirst();
            if (optAnimal.isPresent()) {
                Animal animal = optAnimal.get();
                animal.setName(request.getName());
                animal.setAge(request.getAge());
                animal.setRace(request.getRace());
                animal.setType(request.getType());
                return guardianMapper.toModel(repository.save(guardian));
            }
            throw new ResourceNotFoundException("Animal invalido");
        }
        throw new ResourceNotFoundException("Guardian invalido");
    }

    @Override
    public ResponseEntity<Void> deleteById(String guardianId, String animalId) {
        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            guardian.getAnimals().removeIf(animal -> animalId.equals(animal.getId()));
            repository.save(guardian);

            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("Guardian invalido");
    }
}
