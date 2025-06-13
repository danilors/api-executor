package br.com.api.asset.asset_data_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "asset_data")
public record AssetEntity(
    @Id
    @Column(name = "id", nullable = false)
    String id,

    @Column(name = "name", nullable = false)
    String name,

    @Column(name = "description", nullable = false)
    String description,

    @Column(name = "value", nullable = false)
    Double value,

    @Column(name = "type", nullable = false)
    String type
) {}