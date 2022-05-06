package com.state.fms.controller;

import com.state.fms.model.File;
import com.state.fms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/psa-file")
public class FileController {

    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,@RequestParam("fileId") String fileId) throws IOException {
        return new ResponseEntity<>(fileService.upload(file,fileId), HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("file") MultipartFile file,@RequestParam("fileId") String fileId) throws IOException {
       return new ResponseEntity<>(fileService.update(file,fileId), HttpStatus.OK);
    }

    @GetMapping("/getFile/{id}")
    public ResponseEntity<File> downloadFile(@PathVariable String id) throws IOException {
        File loadFile = fileService.download(id);

        return ResponseEntity.ok().body(loadFile);
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        File loadFile = fileService.download(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFileName() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }

    @GetMapping("/getFileList/{ids}")
    public ResponseEntity<?> downloadFiles(@PathVariable ("ids") List<String> ids) throws IOException {
        List<File> lst=fileService.downloadFiles(ids);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> downloadFiles(@PathVariable ("id") String id) throws IOException {
        fileService.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> downloadFiles(@PathVariable ("id") String id) throws IOException {
      fileService.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }*/
}