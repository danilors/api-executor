package br.com.api.address.address_data_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "address_data")
public record AddressEntity(
    @Id
    @Column(name = "id", nullable = false)
    String id,

    @Column(name = "street", nullable = false)
    String street,

    @Column(name = "number", nullable = false)
    String number,

    @Column(name = "neighborhood", nullable = false)
    String neighborhood,

    @Column(name = "city", nullable = false)
    String city,

    @Column(name = "state", nullable = false)
    String state
) {}