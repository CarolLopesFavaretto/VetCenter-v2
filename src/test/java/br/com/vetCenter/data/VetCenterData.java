package br.com.vetCenter.data;

import br.com.vetCenter.domain.entity.Animal;
import br.com.vetCenter.domain.entity.Consultation;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.domain.entity.Prescription;

import java.time.LocalDate;

public class VetCenterData {

    public static Guardian getGuardian() {
        Guardian guardian = new Guardian();
        guardian.setId("64666b61bbd020544eb2714b");
        guardian.setName("Caroline");
        guardian.setCpf("400287319521");
        guardian.setTelephone(1140543188L);
        guardian.setAnimals(guardian.getAnimals());
        return guardian;
    }

    public static Animal getAnimal() {
        Animal animal = new Animal();
        animal.setId("123456");
        animal.setName("Maria");
        animal.setAge(11);
        animal.setType("felino");
        animal.setRace("Tomba Lata");
        animal.setConsultations(animal.getConsultations());
        return animal;
    }

    public static Consultation getConsultation() {
        Consultation consultation = new Consultation();
        consultation.setId(String.valueOf(1L));
        consultation.setNameVeterinary("Maria");
        consultation.setCause("coluna");
        consultation.setDate(LocalDate.now());
        consultation.setObservations("raio-x realizado");
        consultation.setValue(80.00);
        consultation.setRegress(true);
        return consultation;
    }

    public static Prescription getPrescription() {
        Prescription prescription = new Prescription();
        prescription.setId("123456");
        prescription.setMedication("Dipirona");
        prescription.setDate(LocalDate.now());
        return prescription;
    }
}
