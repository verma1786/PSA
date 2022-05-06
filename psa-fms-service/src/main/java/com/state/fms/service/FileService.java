package com.state.fms.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.state.fms.model.File;
import com.state.fms.repository.FileRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    @Autowired
    FileRepository nRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    public String upload(MultipartFile upload,String fileId) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("fileId",fileId);
        metadata.put("contentType", upload.getContentType());
        metadata.put("fileSize", upload.getSize());
        metadata.put("createdOn", new Date());
        Object fileID = gridFsTemplate.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
        return fileID.toString();
    }

    public String update(MultipartFile upload,String fileId) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne( new Query(Criteria.where("_id").is(fileId)) );

        DBObject metadata = new BasicDBObject();
        Object fileID =null;
        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            metadata.put("fileId",fileId);
            metadata.put("contentType", upload.getContentType());
            metadata.put("fileSize", upload.getSize());
            metadata.put("updatedOn", new Date());
            fileID = gridFsTemplate.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
        }
       return fileID.toString();
    }

    public File download(String id) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne( new Query(Criteria.where("_id").is(id)) );
        File loadFile = new File();
        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFileId(gridFSFile.getMetadata().get("fileId").toString() );
            loadFile.setFileName( gridFSFile.getFilename() );
            loadFile.setFileType( gridFSFile.getMetadata().get("contentType").toString() );
            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );
            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }
        return loadFile;
    }
public byte[] downloadFileInByteArray(String id) throws IOException {
    GridFSFile gridFSFile = gridFsTemplate.findOne( new Query(Criteria.where("_id").is(id)) );
    File loadFile = new File();
    if (gridFSFile != null && gridFSFile.getMetadata() != null) {
        loadFile.setFileName( gridFSFile.getFilename() );
        loadFile.setFileType( gridFSFile.getMetadata().get("contentType").toString() );
        loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );
        loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
    }
    return IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
}
    public GridFSFile  getFileById(String id) {
      return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }
    public InputStream loadResource(String id) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne( new Query(Criteria.where("_id").is(id)) );
        return operations.getResource(gridFSFile).getInputStream();
    }

    public List<File> downloadFiles(List<String> ids) throws IOException {
        GridFSFindIterable files = gridFsTemplate.find(Query.query(Criteria.where("_id").in(ids)));
        File loadFile=null;
        List<File> loadFiles=new ArrayList<>();
        for (GridFSFile gridFSFile : files) {
            System.out.println("file.getFilename() = " + gridFSFile.getFilename());
            loadFile = new File();
            if (gridFSFile != null && gridFSFile.getMetadata() != null) {
                loadFile.setFileId(gridFSFile.getMetadata().get("fileId").toString() );
                loadFile.setFileName( gridFSFile.getFilename() );
                loadFile.setFileType( gridFSFile.getMetadata().get("contentType").toString() );
                loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );
                loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
                loadFiles.add(loadFile);
            }
        }
          return loadFiles;//  nRepository.findByIds(ids);
    }

    public GridFSFile getById(String id) {
        return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    public void deleteById(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }



}

