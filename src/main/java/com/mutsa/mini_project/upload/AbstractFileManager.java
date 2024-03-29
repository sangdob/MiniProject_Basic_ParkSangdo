package com.mutsa.mini_project.upload;

import com.mutsa.mini_project.exceptions.ErrorCode;
import com.mutsa.mini_project.exceptions.ExceptionResponse;
import com.mutsa.mini_project.exceptions.exception.CustomIoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public abstract class AbstractFileManager implements FileManager{
    private final String path;

    public AbstractFileManager(String path) {
        this.path = path;

        if (!hasDir(path)){
            mkdirs(new File(path));
        }
    }

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        try{
            String fileName = file.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            String uuid = UUID.randomUUID() + "." + ext;
            String filePath = path + File.separator + uuid;
            file.transferTo(new File(filePath));

            log.info("file upload success = {}", filePath);
            return uuid;
        } catch (IOException e) {
            throw new CustomIoException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    @Override
    public boolean delete(String fileName) {
        File file = new File(path + File.separator + fileName);
        if (file.exists()) {
            if(file.delete()) {
                log.info("success file delete = {}", file.getAbsolutePath());
                return true;
            }
        }
        log.info("fail file delete = {}", file.getAbsolutePath());
        return false;
    }

    private static boolean hasDir(String path) {
        return new File(path).exists();
    }

    private static void mkdirs(File file) {
        file.mkdirs();
    }

}
