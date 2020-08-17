package com.halaguys.whistleon.service;

public interface EmailService {
    void sendMail(String to) throws Exception;
    void deleteMailCode(String email);
    String getMailCode(String email);
    String makeRandomString();
}
