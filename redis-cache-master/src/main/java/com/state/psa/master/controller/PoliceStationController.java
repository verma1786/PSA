package com.state.psa.master.controller;

import com.state.psa.master.common.constants.Constants;
import com.state.psa.master.common.response.ApiResponse;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.entity.PoliceStation;
import com.state.psa.master.model.response.Response;
import com.state.psa.master.service.PoliceStationService;
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
public class PoliceStationController {
    @Autowired
    private PoliceStationService policeStationService;

    @GetMapping("/policeStations/{id}")
    private ResponseEntity<ApiResponse> getPoliceStations(@PathVariable(value = "id") Integer districtId) {
        log.info("PoliceStationController :getPoliceStations request districtId :: {}",districtId);
        List<PoliceStation> policeStations=  policeStationService.findAll(districtId).stream().sorted((p1, p2) -> (p1.getPs()).compareTo(p2.getPs()))
                .collect(Collectors.toList());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new Response<>(policeStations))
                .build();
        log.info("[PoliceStationController.getPoliceStations] : success response :: "+apiResponse.toString());
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/policeStation/{id}")
    public ResponseEntity<ApiResponse> getPoliceStation(@PathVariable(value = "id") Integer psId) throws Throwable {
        log.info("PoliceStationController :getPoliceStation request psId :: {}",psId);
        PoliceStation policeStation = (PoliceStation) policeStationService.findById(psId).orElseThrow(() -> new ResourceNotFoundException("PoliceStation not found for this id :: " + psId));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(policeStation)
                .build();
        log.info("[PoliceStationController.getPoliceStation] : success response :: {}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }

}
