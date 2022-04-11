package com.state.fms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoadFile {
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
