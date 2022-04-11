package com.state.psa.community.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Valid
@Entity(name="m_member_type")
public class CLGMemberType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_TYPE_CD", length = 11)
    protected Integer id;

    @Column(name="MEMBER_TYPE", length = 100)
    private String memberType;

    @Column(name = "LANG_CD", length = 4)
    protected Integer langId;

    @Column(name = "RECORD_STATUS", length = 11)
    protected String status;

    @JsonIgnore
    @JsonProperty(value = "RECORD_CREATED_DATE")
    @Column(name = "RECORD_CREATED_ON")
    protected String recordCreatedOn;

    @JsonIgnore
    @JsonProperty(value = "RECORD_CREATED_BY")
    @Column(name = "RECORD_CREATED_BY", length = 100)
    protected String recordCreatedBy;

}
