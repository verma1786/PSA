package com.state.psa.lost.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name="t_complainant_detail")
public class ComplainantDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMPLAINANT_ID")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name="COMPLAINANT_NAME" ,length=40 ,nullable = false)
    private String complainantName;

    @Column(name="AADHAAR_UID_NO" ,length=12 ,nullable = false)
    private String aadhaarUIDNo;
    @Column(name="BHAMASHAH_CARD_NO" ,length=40 ,nullable = false)
    private String bhamashahCardNo;
    @Column(name="GENDER_ID" ,length=11 ,nullable = false)
    private Integer genderId;
    @Column(name="GENDER" ,length=11 ,nullable = false)
    private String gender;
    @Column(name="PARENT_NAME" ,length=40 ,nullable = false)
    private String parentName;
    @Column(name="COMPLAINANT_ADDRESS" ,length=100 ,nullable = false)
    private String  complainantAddress;
    @Column(name="COMPLAINANT_MOBILE" ,length=20 ,nullable = false)
    private String complainantMobile;
    @Column(name="COMPLAINANT_EMAIL" ,length=100 ,nullable = false)
    private String complainantEmail;
    @Column(name="PLACE_OF_LOSS" ,length=40 ,nullable = false)
    private String placeOfLoss;
    @Column(name="DATE_OF_LOSS" ,length=12 ,nullable = false)
    private String dateOfLoss;
    @Column(name="TIME_OF_LOSS" ,length=12 ,nullable = false)
    private String timeOfLoss;
    @Column(name="ANY_OTHER_DETAILS" ,length=500 ,nullable = false)
    private String anyOtherDetails;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name="COMPLAINANT_ID")
    private List<LostArticle> articles;
}
