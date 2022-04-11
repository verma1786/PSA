package com.state.psa.master.model.dto.response;

import com.state.psa.master.model.entity.State;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class StateResponse<T> implements Serializable {
    private List<State> states;
}
