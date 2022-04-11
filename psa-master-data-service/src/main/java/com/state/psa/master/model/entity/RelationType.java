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
@Valid
@Entity(name="m_relation_type")
public class RelationType implements Serializable {

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

