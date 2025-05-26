package br.com.executor.api_executor.converter;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class MapConverter {
    public static MultiValueMap<String, String> convertToMultiValueMap(Map<String, String> map) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        map.forEach((key, value) -> multiValueMap.add(key, value));
        return multiValueMap;
    }
}