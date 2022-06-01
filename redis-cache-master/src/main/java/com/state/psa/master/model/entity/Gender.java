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
@Entity(name="m_gender")
public class Gender implements Serializable {

    @Id
    @Column(name = "GENDER_CD", updatable = false)
    protected Integer genderId;

    @Column(name="GENDER", updatable = false)
    private String gender;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;
}
