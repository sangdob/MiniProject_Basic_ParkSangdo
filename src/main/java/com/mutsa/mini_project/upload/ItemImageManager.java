package com.mutsa.mini_project.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
public class ItemImageManager extends AbstractFileManager{
    public ItemImageManager(String path) {
        super(path);
    }

    @Override
    public String upload(MultipartFile file) {
        log.info("uploading item image");
        return super.upload(file);
    }

    @Override
    public boolean delete(String fileName) {
        log.info("delete item image");
        return super.delete(fileName);
    }
}
