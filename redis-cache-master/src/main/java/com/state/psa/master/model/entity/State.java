package com.state.psa.master.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity(name="m_state")
public class State implements Serializable {

    @Id
    @Column(name = "STATE_CD", updatable = false)
    protected Integer stateId;

    @Column(name="STATE", updatable = false)
    private String state;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}
