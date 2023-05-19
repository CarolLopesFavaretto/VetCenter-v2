package br.com.vetCenter.data;

import br.com.vetCenter.domain.entity.Guardian;

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
}
