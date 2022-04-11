package com.state.psa.community.model.dto.response;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@ToString
public class CLGMemberResponse<T> implements Serializable {
    private String name;
}
