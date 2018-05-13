package com.chen.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class StreamUtil {

    public static String getString(InputStream in) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            log.error("getString in inputstream error", e);
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();

    }

}
