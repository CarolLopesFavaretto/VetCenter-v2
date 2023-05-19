package br.com.vetCenter.repository;

import br.com.vetCenter.VetCenterV2Application;
import br.com.vetCenter.config.TestContainerConfig;
import br.com.vetCenter.data.VetCenterData;
import br.com.vetCenter.domain.entity.Guardian;
import br.com.vetCenter.framework.adapter.out.persistence.GuardianRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = VetCenterV2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestContainerConfig.class)
@RunWith(SpringRunner.class)
public class GuardianRepositoryTest {

    @Autowired
    private GuardianRepository repository;


    @Test
    public void runTestGuardian() {

        //      Inserção de dados

        Guardian guardian = VetCenterData.getGuardian();
        repository.save(guardian);

        //      Buscando registros inseridos

        List<Guardian> guardianList = repository.findAll();
        Assert.assertEquals(1, guardianList.size());
        Guardian guardianDb = guardianList.get(0);
        Assert.assertEquals(guardianDb.getName(), guardian.getName());
        Assert.assertEquals(guardianDb.getCpf(), guardian.getCpf());
        Assert.assertEquals(guardianDb.getTelephone(), guardian.getTelephone());

        //      Buscando por id

        Optional<Guardian> guardianOpt = repository.findById(guardian.getId());
        Assert.assertEquals(guardianOpt.isPresent(), true);
        guardianDb = guardianOpt.get();
        Assert.assertEquals(guardianDb.getName(), guardian.getName());
        Assert.assertEquals(guardianDb.getCpf(), guardian.getCpf());
        Assert.assertEquals(guardianDb.getTelephone(), guardian.getTelephone());

        //      Atualização de telephone e validação

        guardian.setName("Lucas");
        guardian.setCpf("400.587.369-08");
        guardian.setTelephone(11947759525L);
        repository.save(guardian);
        guardianOpt = repository.findById(guardian.getId());
        Assert.assertEquals(guardianOpt.isPresent(), true);
        guardianDb = guardianOpt.get();
        Assert.assertEquals(guardianDb.getTelephone(), guardian.getTelephone());
        Assert.assertEquals(guardianDb.getName(), guardian.getName());
        Assert.assertEquals(guardianDb.getCpf(), guardian.getCpf());

        //      Deletando e validando

        repository.deleteById(guardianDb.getId());
        guardianOpt = repository.findById(guardian.getId());
        Assert.assertEquals(guardianOpt.isPresent(), false);
    }
}
