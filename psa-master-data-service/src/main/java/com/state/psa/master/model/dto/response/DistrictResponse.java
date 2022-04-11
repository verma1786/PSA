package com.state.psa.master.model.dto.response;

import com.state.psa.master.model.entity.District;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@Getter
@ToString
public class DistrictResponse<T> implements Serializable {
    private List<District> districts;
}
