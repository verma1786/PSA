package com.state.psa.master.controller;

import com.state.psa.master.common.constants.Constants;
import com.state.psa.master.common.response.ApiResponse;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.entity.District;
import com.state.psa.master.model.response.Response;
import com.state.psa.master.service.DistrictService;
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

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/districts/{id}")
    private ResponseEntity<ApiResponse> getDistricts(@PathVariable(value = "id") Integer stateId) {
        log.info("DistrictController :getDistricts");
        List<District> districts=  districtService.findAll(stateId);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new Response<>(districts))
                .build();
        log.info("[DistrictController.getDistricts] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<ApiResponse> getDistrict(@PathVariable(value = "id") Integer districtId) throws Throwable {
        log.info("DistrictController :getDistrict request districtId :: {}",districtId);
        District district = (District) districtService.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District not found for this id :: " + districtId));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(district)
                .build();
        log.info("[DistrictController.getDistrict] : success response :: {}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }
}
