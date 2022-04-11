package com.state.psa.master.model.dto.response;

import com.state.psa.master.model.entity.Nationality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@ToString
public class NationalityResponse<T> implements Serializable {
    private Nationality nationality;
}
