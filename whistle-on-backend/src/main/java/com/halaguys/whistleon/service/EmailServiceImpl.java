package com.halaguys.whistleon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender emailSender;

    private static final String text = "안녕하세요 휘슬온입니다.\n아래 인증번호를 입력해주세요.\n\n인증번호 : ";
    private static final String subject = "인증 메일 발송 - WhistleOn";
    private static final Map<String,String> mailCode = new HashMap<>();

    public void sendMail(String to) throws Exception{
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            String randomString = makeRandomString();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text+randomString);
            emailSender.send(message);
            if(mailCode.containsKey(to)){
                deleteMailCode(to);
            }
            mailCode.put(to,randomString);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Override
    public void deleteMailCode(String email) {
        mailCode.remove(email);
    }

    @Override
    public String getMailCode(String email){
        return mailCode.get(email);
    }

    @Override
    public String makeRandomString(){
        Random rnd =new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<6;i++){
            if(rnd.nextBoolean()){
                sb.append((char)((rnd.nextInt(26))+65));
            }else{
                sb.append((rnd.nextInt(10)));
            }
        }
        return sb.toString();
    }

}
