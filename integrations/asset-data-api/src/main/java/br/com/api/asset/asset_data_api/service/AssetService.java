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

    public AssetEntity createAsset(AssetEntity assetEntity) {
        return assetRepository.save(assetEntity);
    }

    public AssetEntity updateAsset(String id, AssetEntity assetEntity) {
        if (!assetRepository.existsById(id)) {
            throw new RuntimeException("AssetEntity not found with id: " + id);
        }
        assetEntity = assetEntity.withId(id); // Assuming a method to set the ID
        return assetRepository.save(assetEntity);
    }

    public void deleteAsset(String id) {
        if (!assetRepository.existsById(id)) {
            throw new RuntimeException("AssetEntity not found with id: " + id);
        }
        assetRepository.deleteById(id);
    }
}