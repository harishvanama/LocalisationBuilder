package com.wavemaker;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

/**
 * @author Harish Vanama
 * @since 3/23/2018
 */
public class GoogleTranslator implements Translator {

    private static final String GOOGLE_TRANSLATE_API = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=";

    private static final Logger logger = LoggerFactory.getLogger(Translator.class);

    @Override
    public String translate(final String value, final String language) {

        String replacedValue = replaceSpecialChars(value);
        String googleTranslateUrl = GOOGLE_TRANSLATE_API + language + "&dt=t&q=" + replacedValue;

        RestConnector restConnector = new RestConnector();
        final String responseBody = restConnector.fetchApiResponse(googleTranslateUrl);

        String translatedData = JsonPath.read(responseBody, "$[0][0][0]");
        translatedData = replaceCharsWithActualData(translatedData);
        logger.info("Translated content for string '{}' with language {} to '{}' ", value, language,
                translatedData);
        return translatedData;

    }


    private static String replaceSpecialChars(String value) {
        for (int i = 0; i < 5; i++) {
            value = value.replace("${" + i + "}", "var" + i);
        }
        value = encodeSpecialChars(value);
        return value;
    }

    private static String replaceCharsWithActualData(String value) {
        for (int i = 0; i < 5; i++) {
            value = value.replace("var" + i, "${" + i + "}");
        }
        value = decodeSpecialChars(value);
        return value;
    }

    private static String encodeSpecialChars(String value) {
        final Map<String, String> specialChars = getSpecialChars();
        for (Map.Entry<String,String> entry:specialChars.entrySet()) {
            value = value.replace(entry.getKey(), entry.getValue());
        }
        return value;
    }

    private static String decodeSpecialChars(String value) {
        final Map<String, String> specialChars = getSpecialChars();
        for (Map.Entry<String,String> entry:specialChars.entrySet()) {
            value = value.replace(entry.getValue(), entry.getKey());
        }
        return value;
    }

    private static Map<String ,String > getSpecialChars(){
        Map<String ,String> specialChars = new HashMap<>();
        specialChars.put("{pid}", "pid1");
        specialChars.put("%", "var8");
        specialChars.put("{0}", "vardot0");
        specialChars.put("{1}", "vardot1");
        specialChars.put("{2}", "vardot2");
        specialChars.put("&", "and");
        return specialChars;
    }

}
