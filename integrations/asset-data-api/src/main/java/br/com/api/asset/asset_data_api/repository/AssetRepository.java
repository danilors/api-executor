package br.com.api.asset.asset_data_api.repository;

import br.com.api.asset.asset_data_api.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<AssetEntity, String> {
}