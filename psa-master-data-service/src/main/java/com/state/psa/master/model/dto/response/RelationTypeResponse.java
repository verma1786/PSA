package com.state.psa.master.model.dto.response;

import com.state.psa.master.model.entity.RelationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelationTypeResponse<T> implements Serializable {
    private List<RelationType> relationTypes;
}
