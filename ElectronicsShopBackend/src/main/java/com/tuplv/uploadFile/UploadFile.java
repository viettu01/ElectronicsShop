package com.tuplv.uploadFile;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

@Component
public class UploadFile {
    public String uploadFile(ServletContext context, String resources, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                File destinationFile = new File(context.getRealPath(resources) + File.separator + System.currentTimeMillis() + "_" + originalFilename);
                file.transferTo(destinationFile);

                return destinationFile.getName();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return null;
    }
}
