package br.com.api.personal.personal_data_api.controller;

import br.com.api.personal.personal_data_api.entity.PersonalEntity;
import br.com.api.personal.personal_data_api.service.PersonalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping("/{id}")
    public PersonalEntity getPersonalById(
            @PathVariable String id,
            @RequestHeader(value = "x-request-id") String requestId
    ) {
        logger.info("Received request to get PersonalEntity with id: {} and requestId: {}", id, requestId);
        return personalService.getPersonalById(id);
    }
}