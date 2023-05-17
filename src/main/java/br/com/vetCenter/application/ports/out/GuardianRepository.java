package br.com.vetCenter.application.ports.out;

import br.com.vetCenter.domain.entity.Guardian;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianRepository extends MongoRepository<Guardian, String> {
}
