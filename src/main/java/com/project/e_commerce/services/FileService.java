package com.project.e_commerce.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final String uploadDir = "uploads/products/";

    public String saveImage(MultipartFile file)throws Exception{
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

    String originalFileName = file.getOriginalFilename();
    String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

    Path filePath = uploadPath.resolve(uniqueFileName);
    Files.copy(file.getInputStream(), filePath);
    return uniqueFileName;
   }
}
