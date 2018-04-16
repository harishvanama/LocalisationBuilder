package com.wavemaker;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Harish Vanama
 * @since 3/22/2018
 */
public class TargetLanguageJsonBuilder {

    private Translator googleTranslator = new GoogleTranslator();

    /***
     * Creates target language json
     * @param sourceObject
     * @param language to be converted
     * @param targetObject
     * @param regenerate if set true then entrire target object will be regenerated
     * @return
     * @throws IOException
     */
    public Map<String, Object> createTargetLanguageMap(Map<String, Object> sourceObject, String language,
            Map<String, Object> targetObject, boolean regenerate) throws IOException {

        Map<String, Object> updatedMap = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : sourceObject.entrySet()) {
            String key = entry.getKey();
            Object childValue = sourceObject.get(key);
            Object updatedValue;
            if (childValue instanceof String) {
                String existingValue = getStringValue(targetObject, key);
                if (existingValue == null || regenerate) {
                    updatedValue = googleTranslator.translate((String) childValue, language);
                } else {
                    updatedValue = existingValue;
                }
            } else if (childValue instanceof Map) {
                Map existingTargetValue = getMapValue(targetObject, key);
                updatedValue = createTargetLanguageMap((Map<String, Object>) childValue, language,
                        existingTargetValue, regenerate);
            } else {
                throw new RuntimeException("Unhandled value type: " + childValue.getClass());
            }
            updatedMap.put(key, updatedValue);
        }
        return updatedMap;
    }

    private String getStringValue(Map map, String key) {
        if (map == null) {
            return null;
        }
        Object value = map.get(key);
        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    private Map getMapValue(Map map, String key) {
        if (map == null) {
            return null;
        }
        Object value = map.get(key);
        if (value instanceof Map) {
            return (Map) value;
        }
        return null;
    }
}
