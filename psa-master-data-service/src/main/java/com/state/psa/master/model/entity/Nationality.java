package com.state.psa.master.model.entity;

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
public class Nationality {
    @Id

    @Column(name = "nationality_cd", updatable = false)
    protected Integer nationalityId;
    @Column(name="nationality")
    private String nationality;
}
