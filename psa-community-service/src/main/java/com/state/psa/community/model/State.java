package com.state.psa.community.model;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class State implements Serializable {
    protected Integer stateId;
    private String state;
    protected Integer langId;
    protected String status;

}
