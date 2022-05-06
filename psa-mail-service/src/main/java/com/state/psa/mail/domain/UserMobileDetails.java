package com.state.psa.mail.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Valid
@Builder
@Entity(name="t_user_mobile")
public class UserMobileDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "T_PSA_SEQUENCE")
    @TableGenerator(name="T_PSA_SEQUENCE", table="T_PSA_SEQUENCE", schema="psa_state_db",allocationSize = 1)
    @Column(name = "id", updatable = false,unique=true, nullable=false)
    protected Long id;
    @Column(name = "user_id", updatable = false,unique=true, nullable=false,length=50)
    public Long userId;
    @Column(name = "user_mobile", updatable = false,unique=true, nullable=false, length=10)
    public String userMobile;

}
