package br.com.vetCenter.application.services;

import br.com.vetCenter.application.ports.in.PrescriptionService;
import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.domain.entity.Consultation;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.domain.entity.Prescription;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.GuardianMapper;
import br.com.vetCenter.framework.adapter.in.dtos.mappers.PrescriptionMapper;
import br.com.vetCenter.framework.adapter.in.dtos.request.PrescriptionRequest;
import br.com.vetCenter.framework.adapter.in.dtos.response.GuardianResponse;
import br.com.vetCenter.framework.adapter.out.persistence.GuardianRepository;
import br.com.vetCenter.framework.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private GuardianRepository repository;

    @Autowired
    private GuardianMapper guardianMapper;

    @Autowired
    private PrescriptionMapper prescriptionMapper;


    @Override
    public GuardianResponse create(String guardianId, String animalId, String consultationId,
                                   PrescriptionRequest request) {

        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            Optional<Animal> optAnimal = guardian.getAnimals().stream()
                    .filter(animal -> animal.getId().equals(animalId)).findFirst();
            if (optAnimal.isPresent()) {
                Animal animal = optAnimal.get();
                Optional<Consultation> optionalConsultation = animal.getConsultations().stream()
                        .filter(consultation -> consultation.getId().equals(consultationId)).findFirst();
                if (optionalConsultation.isPresent()) {
                    Consultation consultation = optionalConsultation.get();

                    Prescription prescription = prescriptionMapper.toObject(request);
                    prescription.setId(UUID.randomUUID().toString());
                    consultation.getPrescriptions().add(prescription);
                    return guardianMapper.toModel(repository.save(guardian));
                }
                throw new ResourceNotFoundException("Consultation invalid");
            }
            throw new ResourceNotFoundException("Animal invalid");
        }
        throw new ResourceNotFoundException("Guardian invalid");
    }

    @Override
    public GuardianResponse update(String guardianId, String animalId, String consultationId, String prescriptionId,
                                   PrescriptionRequest request) {

        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            Optional<Animal> optAnimal = guardian.getAnimals().stream()
                    .filter(animal -> animal.getId().equals(animalId)).findFirst();
            if (optAnimal.isPresent()) {
                Animal animal = optAnimal.get();
                Optional<Consultation> optionalConsultation = animal.getConsultations().stream()
                        .filter(consultation -> consultation.getId().equals(consultationId)).findFirst();
                if (optionalConsultation.isPresent()) {
                    Consultation consultation = optionalConsultation.get();
                    Optional<Prescription> optionalPrescription = consultation.getPrescriptions().stream()
                            .filter(prescription -> prescription.getId().equals(prescriptionId)).findFirst();
                    if (optionalPrescription.isPresent()) {
                        Prescription prescription = optionalPrescription.get();
                        prescription.setDate(request.getDate());
                        prescription.setMedication(request.getMedication());
                        return guardianMapper.toModel(repository.save(guardian));
                    }
                    throw new ResourceNotFoundException("Prescription invalid");
                }
                throw new ResourceNotFoundException("Consultation invalid");
            }
            throw new ResourceNotFoundException("Animal invalid");
        }
        throw new ResourceNotFoundException("Guardian invalid");
    }

    @Override
    public ResponseEntity<Void> deleteById(String guardianId, String animalId, String consultationId,
                                           String prescriptionId) {
        Optional<Guardian> optionalGuardian = repository.findById(guardianId);
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            Optional<Animal> optAnimal = guardian.getAnimals().stream()
                    .filter(animal -> animal.getId().equals(animalId)).findFirst();
            if (optAnimal.isPresent()) {
                Animal animal = optAnimal.get();
                Optional<Consultation> optionalConsultation = animal.getConsultations().stream()
                        .filter(consultation -> consultation.getId().equals(consultationId)).findFirst();
                if (optionalConsultation.isPresent()) {
                    Consultation consultation = optionalConsultation.get();
                    consultation.getPrescriptions().removeIf(prescription ->
                            prescriptionId.equals(prescription.getId()));
                    repository.save(guardian);
                }
                return ResponseEntity.noContent().build();
            }
            throw new ResourceNotFoundException("Animal invalid");
        }
        throw new ResourceNotFoundException("Guardian invalid");
    }
}
