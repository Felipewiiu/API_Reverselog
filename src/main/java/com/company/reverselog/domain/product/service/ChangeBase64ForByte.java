package com.company.reverselog.domain.product.service;

import java.util.Base64;
import java.util.regex.Pattern;

public class ChangeBase64ForByte {

    private static final Pattern BASE64_PATTERN = Pattern.compile("^[A-Za-z0-9+/=]+$");

    public static byte[]  changeBase64(String image){

        if (!BASE64_PATTERN.matcher(image).matches()) {
            throw new IllegalArgumentException("String contém caracteres inválidos para Base64");
        }

        byte[] imageBytes = Base64.getDecoder().decode(image);

        return imageBytes;

    }
}
