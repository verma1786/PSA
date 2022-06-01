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
@Entity(name="m_relation_type")
public class Relation implements Serializable {

    @Id
    @Column(name = "RELATION_TYPE_CD", updatable = false)
    protected Integer id;

    @Column(name="RELATION_TYPE")
    private String relationType;


    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}

