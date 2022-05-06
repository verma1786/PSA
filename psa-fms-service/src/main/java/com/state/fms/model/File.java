package com.state.fms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class File {
    @Id
    private String fileId;
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
