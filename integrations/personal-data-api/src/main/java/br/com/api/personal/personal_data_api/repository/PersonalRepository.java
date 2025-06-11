package br.com.api.personal.personal_data_api.repository;

import br.com.api.personal.personal_data_api.entity.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PersonalRepository extends JpaRepository<PersonalEntity, String> {
}