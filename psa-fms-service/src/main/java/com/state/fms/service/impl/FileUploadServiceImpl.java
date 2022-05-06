package com.state.fms.service.impl;

import java.io.IOException;

import com.state.fms.service.FileUploadService;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Vinay Kumar
 * @since 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final GridFsTemplate gridFsTemplate;

    private final DBObject dbObject;

    public FileUploadServiceImpl(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
        dbObject = new BasicDBObject();
    }

    @Override
    public void uploadFile(MultipartFile multipartFile) throws IOException {
        setDbObject(multipartFile);
        gridFsTemplate.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), dbObject);
    }

    private void setDbObject(MultipartFile multipartFile) {
        dbObject.put("fileName", multipartFile.getOriginalFilename());
        dbObject.put("contentType", multipartFile.getContentType());
        dbObject.put("size", multipartFile.getSize());
    }
}