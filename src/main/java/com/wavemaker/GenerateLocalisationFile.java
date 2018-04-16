package com.wavemaker;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Harish Vanama
 * @since 3/22/2018
 */
public class GenerateLocalisationFile {

    private static TargetLanguageJsonBuilder targetLanguageJsonBuilder = new TargetLanguageJsonBuilder();
    private static final Logger logger = LoggerFactory.getLogger(GenerateLocalisationFile.class);

    public static void main(String[] args) throws IOException {

        String sourceJson = args[0];
        String targetJson = args[1];
        String language = args[2];
        Boolean regenerate = Boolean.valueOf(args[3]);

        if (StringUtils.isEmpty(targetJson)) {
            final String pathname = "./target/" + language + ".json";
            final File file = new File(pathname);
            targetJson = file.getAbsolutePath();
        }

        //Read from string
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> sourceObject = objectMapper.readValue(new File(sourceJson), Map.class);
        Map<String, Object> targetObject = null;

        //Read from existing file if present
        final File targetJsonFile = new File(targetJson);
        if (targetJsonFile.exists()) {
            targetObject = objectMapper.readValue(targetJsonFile, Map.class);
        } else {
            targetObject = new LinkedHashMap<>();
        }

        targetObject = targetLanguageJsonBuilder
                .createTargetLanguageMap(sourceObject, language, targetObject, regenerate);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(targetJsonFile, targetObject);
        logger.info("Target Json file for language {} has been saved at {}", language,
                targetJsonFile.getAbsolutePath());
    }

}


