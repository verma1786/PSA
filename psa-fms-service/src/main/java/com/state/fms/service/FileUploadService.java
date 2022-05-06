package com.state.fms.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Vinay Kumar
 * @since 1.0
 */
public interface FileUploadService {

    void uploadFile(MultipartFile multipartFile) throws IOException;

}