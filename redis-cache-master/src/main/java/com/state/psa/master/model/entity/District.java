package com.state.psa.master.model.entity;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;



@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity(name="m_district")
public class District implements Serializable {

    @Id
    @Column(name = "DISTRICT_CD", updatable = false)
    protected Integer id;

    @Column(name="DISTRICT", updatable = false)
    private String district;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "STATE_CD", updatable = false)
    protected Integer stateId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}
