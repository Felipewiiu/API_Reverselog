package com.company.reverselog.domain.product.service;

import java.util.Base64;

public class ChangeBase64ForByte {

    public static byte[]  changeBase64(String image){

        byte[] imageBytes = Base64.getDecoder().decode(image);
        System.out.println("---->" + imageBytes.length);
        return imageBytes;

    }
}
