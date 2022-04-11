package com.state.psa.master.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Valid
@Entity(name="m_district")
public class District implements Serializable {

    @Id
    @Column(name = "district_cd", updatable = false)
    protected Integer id;

    @Column(name="district")
    private String district;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "state_cd", updatable = false)
    protected Integer stateId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}
