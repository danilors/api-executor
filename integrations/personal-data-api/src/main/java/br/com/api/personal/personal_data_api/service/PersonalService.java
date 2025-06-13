package br.com.api.personal.personal_data_api.service;

import br.com.api.personal.personal_data_api.entity.PersonalEntity;
import br.com.api.personal.personal_data_api.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public PersonalEntity getPersonalById(String id) {
        return personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PersonalEntity not found with id: " + id));
    }
}