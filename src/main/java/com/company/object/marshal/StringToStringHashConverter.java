package com.company.object.marshal;

import javax.persistence.AttributeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringToStringHashConverter implements AttributeConverter<String, String> {

    private static final String EMPTY_STRING = "";

    @Override
    public String convertToDatabaseColumn(String object) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(object.toString()));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return EMPTY_STRING;
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return EMPTY_STRING;
    }
}
