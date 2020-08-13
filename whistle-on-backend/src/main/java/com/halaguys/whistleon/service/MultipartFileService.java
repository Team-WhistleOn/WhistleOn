package com.halaguys.whistleon.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MultipartFileService {
    public List<String> uploadFiles(List<MultipartFile> files);
    public String uploadFile(MultipartFile file);
}
