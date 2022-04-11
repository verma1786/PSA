package com.state.psa.master.controller;

import com.state.psa.master.model.dto.response.ApiResponse;
import com.state.psa.master.model.dto.response.StateResponse;
import com.state.psa.master.model.entity.District;
import com.state.psa.master.service.DistrictService;
import com.state.psa.master.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class DistrictController {
    @Autowired
    DistrictService districtService;

    @GetMapping("/district/{id}")
    public ResponseEntity<ApiResponse> getAllDistrictByStateId(@PathVariable("id") Integer id) {
        log.info("DistrictController :getAllDistrictByStateId request id {} ", id);

        List<District> filteredList = districtService.findById(id).stream()
                .filter(e -> "C".equalsIgnoreCase(e.getStatus()) && e.getDistrict().length() > 1)
                .collect(Collectors.toList());

        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new StateResponse(filteredList))
                .build();
        log.info("[DistrictController.getAllDistrictByStateId] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
}
