package com.state.psa.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class LoadFile implements Serializable {
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
