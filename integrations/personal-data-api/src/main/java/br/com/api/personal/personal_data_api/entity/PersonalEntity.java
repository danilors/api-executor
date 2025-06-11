package br.com.api.personal.personal_data_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "personal_data")
public record PersonalEntity(
    @Id
    @Column(name = "id", nullable = false)
    String id,

    @Column(name = "name", nullable = false)
    String name,

    @Column(name = "email", nullable = false)
    String email,

    @Column(name = "birthdate", nullable = false)
    LocalDate birthdate,

    @Column(name = "phonenumber")
    String phonenumber
) {}