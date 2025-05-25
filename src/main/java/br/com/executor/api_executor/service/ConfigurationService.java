package br.com.executor.api_executor.service;

import br.com.executor.api_executor.model.ConfigData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    private final ObjectMapper objectMapper;

    public ConfigurationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<ConfigData> getConfiguration(String key) {
        logger.info("Fetching configuration for key: {}", key);
        try {
            ConfigData configData = objectMapper.readValue(
                    Paths.get("src/main/resources/config.json").toFile(),
                    ConfigData.class
            );
            logger.info("Configuration loaded successfully: {}", configData);
            return Optional.of(configData);
        } catch (IOException e) {
            logger.warn("Error reading configuration file", e);
            return Optional.empty();
        }
    }


}
