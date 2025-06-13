package br.com.api.asset.asset_data_api.service;

import br.com.api.asset.asset_data_api.entity.AssetEntity;
import br.com.api.asset.asset_data_api.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<AssetEntity> getAllAssets() {
        return assetRepository.findAll();
    }

    public AssetEntity getAssetById(String id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AssetEntity not found with id: " + id));
    }



}