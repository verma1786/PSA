package com.state.psa.community.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Valid
@Entity(name="t_clg_meeting")
public class CLGMeetingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "T_PSA_SEQUENCE")
    @TableGenerator(name="T_PSA_SEQUENCE", table="T_PSA_SEQUENCE", schema="psa_state_db",allocationSize = 1)
    @Column(name = "clg_cd", updatable = false,unique=true, nullable=false)
    protected Long id;
    @Column(name="meeting_date")
    private String meetingDate;
}
