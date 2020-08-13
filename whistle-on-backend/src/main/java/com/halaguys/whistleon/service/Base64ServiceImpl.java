package com.halaguys.whistleon.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Base64ServiceImpl implements Base64Service {
    @Override
    public String encode(String key) {
        byte[] message = key.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(message);
    }

    @Override
    public String decode(String key) {
        byte[] message = Base64.getDecoder().decode(key);
        return new String(message,StandardCharsets.UTF_8);
    }
}
