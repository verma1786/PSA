package com.state.psa.master.controller;

import com.state.psa.master.model.dto.response.ApiResponse;
import com.state.psa.master.model.dto.response.PoliceStationResponse;
import com.state.psa.master.model.entity.PoliceStation;
import com.state.psa.master.service.PoliceStationService;
import com.state.psa.master.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class PoliceStationController {
    @Autowired
    PoliceStationService policeStationService;

    @GetMapping("/ps")
    public ResponseEntity<ApiResponse> getAllPoliceStation(@RequestParam Integer districtId, @RequestParam Integer langId) {
        log.info("PoliceStationController :getAllPoliceStationByDistrictId request id {} ", districtId);
        List<PoliceStation> filteredList = policeStationService.findPoliceStations(districtId,langId).stream()
                .filter(e -> "C".equalsIgnoreCase(e.getStatus()) && e.getPs().length() > 1)
                .collect(Collectors.toList());

        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new PoliceStationResponse(filteredList))
                .build();
        log.info("[PoliceStationController.getAllPoliceStationByDistrictId] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
}
