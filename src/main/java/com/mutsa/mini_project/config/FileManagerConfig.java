package com.mutsa.mini_project.config;

import com.mutsa.mini_project.upload.ItemImageManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

@Configuration
@Slf4j
public class FileManagerConfig {

    @Bean
    public ItemImageManager itemImageManager(ResourceLoader resourceLoader) throws IOException {
        String ITEM_IMAGE_FOLDER_PATH = File.separator + "images" + File.separator + "items";
        File root = resourceLoader.getResource("file:").getFile();
        String absoluteImagePath = root.getAbsolutePath() + ITEM_IMAGE_FOLDER_PATH;
        log.info(absoluteImagePath);
        return new ItemImageManager(absoluteImagePath);
    }
}
