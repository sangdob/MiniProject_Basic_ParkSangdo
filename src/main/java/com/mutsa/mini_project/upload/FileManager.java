package com.mutsa.mini_project.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileManager {
    String upload(MultipartFile file) throws IOException;
    boolean delete(String fileName);
}
