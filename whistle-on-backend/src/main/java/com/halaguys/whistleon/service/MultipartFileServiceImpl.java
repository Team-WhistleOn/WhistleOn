package com.halaguys.whistleon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MultipartFileServiceImpl implements  MultipartFileService{

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> paths =new ArrayList<>();

        for(MultipartFile file : files){
            String path = uploadFile(file);
            paths.add(path);
        }
        return paths;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName=file.getOriginalFilename();
        String now=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String path=now+"_"+fileName;
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
