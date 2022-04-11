package com.state.fms.controller;

import com.state.fms.model.LoadFile;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/psa-file")
public class FileController {

    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileService.upload(file), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        LoadFile loadFile = fileService.download(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFileName() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }

    @GetMapping("/getFileList/{ids}")
    public ResponseEntity<?> downloadFiles(@PathVariable ("ids") List<String> ids) throws IOException {
        List<LoadFile> lst=fileService.downloadFiles(ids);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

}