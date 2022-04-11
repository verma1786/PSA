package com.state.psa.master.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
@Getter
@Builder
@ToString
public class ApiResponse<T> {
    String message;
    String status;
    List<String> errors;
    T data;
}
