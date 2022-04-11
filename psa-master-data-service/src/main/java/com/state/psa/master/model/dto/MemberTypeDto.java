package com.state.psa.master.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberTypeDto implements Serializable {

    protected Integer id;
    private String relationType;
    protected Integer langId;
    protected String status;
    protected String recordCreatedDate;
    protected String recordCreatedBy;

}
