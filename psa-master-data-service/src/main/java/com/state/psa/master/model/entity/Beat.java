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
@Entity(name="m_ps_beat")
public class Beat implements Serializable {

    @Id
    @Column(name = "BEAT_CD", updatable = false)
    protected Integer stateId;

    @Column(name="BEAT_NAME", updatable = false)
    private String beatName;

    @Column(name="PS_CD", updatable = false)
    private String psId;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}
