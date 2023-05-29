package br.com.vetCenter.application.services;

import br.com.vetCenter.application.ports.in.ConsultationService;
import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.domain.entity.Consultation;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.ConsultationMapper;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.GuardianMapper;
import br.com.vetCenter.framework.adapter.in.dtos.request.ConsultationRequest;
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
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private GuardianRepository repository;

    @Autowired
    private GuardianMapper guardianMapper;

    @Autowired
    private ConsultationMapper consultationMapper;

    @Override
    public GuardianResponse create(String guardianId, String animalId, ConsultationRequest request) {
        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            Optional<Animal> optAnimal = guardian.getAnimals().stream()
                    .filter(animal -> animal.getId().equals(animalId)).findFirst();
            if (optAnimal.isPresent()) {
                Animal animal = optAnimal.get();
                if (animal.getConsultations() == null) {
                    animal.setConsultations(new ArrayList<>());
                }
                Consultation consultation = consultationMapper.toObject(request);
                consultation.setId(UUID.randomUUID().toString());
                animal.getConsultations().add(consultation);
                return guardianMapper.toModel(repository.save(guardian));
            }
        }
        throw new ResourceNotFoundException("Guardian invalid");
    }


    @Override
    public GuardianResponse update(String guardianId, String animalId, String consultationId, ConsultationRequest
            request) {
        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            Optional<Animal> optAnimal = guardian.getAnimals().stream()
                    .filter(animal -> animalId.equals(animal.getId())).findFirst();
            if (optAnimal.isPresent()) {
                Animal animal = optAnimal.get();
                Optional<Consultation> optConsult = animal.getConsultations().stream().filter(
                        consultation -> consultationId.equals(consultation.getId())).findAny();
                if (optConsult.isPresent()) {
                    Consultation consultation = optConsult.get();
                    consultation.setCause(request.getCause());
                    consultation.setNameVeterinary(request.getNameVeterinary());
                    consultation.setObservations(request.getObservations());
                    consultation.setRegress(request.getRegress());
                    consultation.setDate(request.getDate());
                    consultation.setValue(request.getValue());
                    return guardianMapper.toModel(repository.save(guardian));
                }
                throw new ResourceNotFoundException("Consultation invalid");
            }
            throw new ResourceNotFoundException("Animal invalid");
        }
        throw new ResourceNotFoundException("Guardian invalid");
    }

    @Override
    public ResponseEntity<Void> deleteById(String guardianId, String animalId, String consultationId) {
        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            Optional<Animal> optAnimal = guardian.getAnimals().stream()
                    .filter(animal -> animalId.equals(animal.getId())).findFirst();
            if (optAnimal.isPresent()) {
                Animal animal = optAnimal.get();
                animal.getConsultations().removeIf(consultation -> consultationId.equals(consultation.getId()));
                repository.save(guardian);
            }
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("Guardian invalid");
    }
}
