package com.sapa.app.common.identifier;


import org.apache.commons.lang3.RandomStringUtils;

public class RefUtil {
    public static String generateRef(){
        return RandomStringUtils.randomAlphanumeric(6);
    }
}