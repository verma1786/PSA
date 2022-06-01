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
@Entity(name="m_occupation")
public class Occupation implements Serializable {
    @Id
    @Column(name = "OCCUPATION_CD", updatable = false)
    protected Integer occupationId;

    @Column(name="OCCUPATION", updatable = false)
    private String occupation;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}
