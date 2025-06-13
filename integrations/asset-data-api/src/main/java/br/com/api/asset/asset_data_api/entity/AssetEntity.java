package br.com.api.asset.asset_data_api.entity;

import jakarta.persistence.*;

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

        @Enumerated(EnumType.STRING)
        @Column(name = "type", nullable = false)
        AssetType type,

        @Column(name = "personal_id", nullable = false)
        String personalId

) {
    public enum AssetType {
        TV,
        HOUSE,
        CAR,
        VIDEO_GAME
    }
}