package com.state.psa.master.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity(name="m_police_station")
public class PoliceStation implements Serializable {

    @Id
    @Column(name = "PS_CD", updatable = false)
    protected Integer psId;

    @Column(name="PS", updatable = false)
    protected String ps;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "DISTRICT_CD", updatable = false)
    protected Integer districtId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}
