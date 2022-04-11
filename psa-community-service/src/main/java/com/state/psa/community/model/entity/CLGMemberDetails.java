package com.state.psa.community.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Valid
@Builder
@Entity(name="t_clg_member")
public class CLGMemberDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "T_PSA_SEQUENCE")
    @TableGenerator(name="T_PSA_SEQUENCE", table="T_PSA_SEQUENCE", schema="psa_state_db",allocationSize = 1)
    @Column(name = "clg_cd", updatable = false,unique=true, nullable=false)
    protected Long id;

    @Column(name="person_name", length = 100, nullable = true, unique = false)
    @NotBlank(message = "{person.name.notBlank}")
    private String personName;
    @Column(name="Occupation_cd")
    private Integer occupationId;
    @Column(name="occupation")
    private String occupation;
    @Column(name="relative_name", length = 100, nullable = true, unique = false)
    private String relativeName;
    @Column(name="mobile_num", length = 10, nullable = true, unique = false)
    @NotBlank(message = "{mobile.number.notBlank}")
    private String mobileNumber;
    @Column(name="landline_STD")
    private String landlineSTD;

    @Column(name="landline_num")
    private String landlineNumber;
    @Column(name="from_date")
    private Date  fromDate;
    @Column(name="address")
    private String address;
    @Column(name="country_cd")
    private Integer countryId;
    @Column(name="country")
    private String country;
    @Column(name="state_cd")
    private Integer stateId;
    @Column(name="state")
    private String state;
    @Column(name="district_cd")
    private Integer districtId;
    @Column(name="district")
    private String district;
    @Column(name="ps_cd")
    private Integer psId;
    @Column(name="ps")
    private String ps;
    @JsonProperty
    private byte[] file;
    @Column(name="city")
    private String city;
    @Column(name="beat_cd")
    private String beatId;
    @Column(name="beat")
    private String beat;
    @Column(name="details")
    private String details;
    @Column(name="file_id")
    private String fileId;
    @Column(name="till_date")
    private Date  tillDate;
    @Column(name="type_cd")
    private Integer typeId;

    @Column(name="TYPE")
    private String memberType;

    @Column(name="is_deleted")
    private Character isDeleted;
    @Column(name="record_created_by")
    private String recordCreatedBy;
    @Column(name="record_created_on")
    private Date  recordCreatedOn;
    @Column(name="record_updated_By")
    private String recordUpdatedBy;
    @Column(name="record_updated_on")
    private Date  recordUpdatedOn;
}
