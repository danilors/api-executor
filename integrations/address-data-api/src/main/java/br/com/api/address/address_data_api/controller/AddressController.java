package br.com.api.address.address_data_api.controller;

import br.com.api.address.address_data_api.entity.AddressEntity;
import br.com.api.address.address_data_api.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public AddressEntity getAddressById(@PathVariable String id, @RequestHeader(value = "x-request-id") String requestId) {
        logger.info("Received request to get AddressEntity with id: {} and requestId: {}", id, requestId);

        return addressService.getAddressById(id);
    }

}