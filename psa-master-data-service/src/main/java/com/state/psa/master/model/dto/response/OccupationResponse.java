package com.state.psa.master.model.dto.response;

import com.state.psa.master.model.entity.Occupation;
import com.state.psa.master.model.entity.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OccupationResponse<T> implements Serializable {
    private List<Occupation> occupations;
}
