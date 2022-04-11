package com.state.psa.master.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Valid
@Entity(name="m_state")
public class State implements Serializable {

    @Id
    @Column(name = "state_cd", updatable = false)
    protected Integer stateId;

    @Column(name="state")
    private String state;

    @Column(name = "LANG_CD", updatable = false)
    protected Integer langId;

    @Column(name = "RECORD_STATUS", updatable = false)
    protected String status;

}
