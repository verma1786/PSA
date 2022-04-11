package com.state.psa.master.model.dto.response;

import com.state.psa.master.model.entity.PoliceStation;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PoliceStationResponse<T> implements Serializable {
    private List<PoliceStation> policeStations;
}
