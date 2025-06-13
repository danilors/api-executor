package br.com.api.address.address_data_api.repository;

import br.com.api.address.address_data_api.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, String> {
}