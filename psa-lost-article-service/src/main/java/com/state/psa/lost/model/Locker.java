package com.state.digi.locker.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Setter
@Getter
public class Locker implements Serializable {
    private String aadhaarId;
}
