package br.com.api.asset.asset_data_api.controller;

import br.com.api.asset.asset_data_api.entity.AssetEntity;
import br.com.api.asset.asset_data_api.service.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<AssetEntity> getAllAssets() {
        logger.info("Received request to get all assets");
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public AssetEntity getAssetById(@PathVariable String id, @RequestHeader(value = "x-request-id") String requestId) {
        logger.info("Received request to get AssetEntity with id: {} and requestId: {}", id, requestId);
        return assetService.getAssetById(id);
    }
 
}