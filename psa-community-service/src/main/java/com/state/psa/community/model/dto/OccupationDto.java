package com.state.psa.community.model.dto;

import lombok.*;

import java.io.Serializable;



@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class OccupationDto implements Serializable {
    protected Integer occupationId;
    private String occupation;
    protected Integer langId;
    protected String status;

}
