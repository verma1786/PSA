package com.state.psa.community.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Validated
public class CLGMemberRequest implements Serializable {
    protected Long id;
    @NotBlank(message = "{image.file.notBlank}")
    private MultipartFile file;

    @NotBlank(message = "{person.name.notBlank}")
    private String personName;

    private Integer occupationId;

    private String occupation;

    private String relativeName;

    @NotBlank(message = "{mobile.number.notBlank}")
    private String mobileNumber;

    private String landlineSTD;


    private String landlineNumber;

    private Date  fromDate;

    private String address;

    private Integer countryId;

    private String country;

    private Integer stateId;

    private String state;

    private Integer districtId;

    private String district;

    private Integer psId;

    private String ps;

    private String city;

    private String beatId;

    private String beat;

    private String details;

    private String fileId;

    private Date  tillDate;

    private Integer typeId;


    private String memberType;


    private Character isDeleted;

    private String recordCreatedBy;

    private Date  recordCreatedOn;

    private String recordUpdatedBy;

    private Date  recordUpdatedOn;

}
