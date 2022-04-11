package com.state.psa.master.controller;


import com.state.psa.master.constants.Constants;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.dto.response.ApiResponse;
import com.state.psa.master.model.dto.response.OccupationResponse;
import com.state.psa.master.model.entity.Occupation;
import com.state.psa.master.service.OccupationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class OccupationController {
    @Autowired
    OccupationService occupationService;

    @GetMapping("/occupations")
    public ResponseEntity<ApiResponse> getAllOccupation() {
        log.info("OccupationController :getAllOccupation");
        List<Occupation> occupations=  occupationService.findAll().stream()
                .filter(e -> "C".equalsIgnoreCase(e.getStatus()) && e.getOccupation().length() > 1)
                .collect(Collectors.toList());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new OccupationResponse<>(occupations))
                .build();
        log.info("[OccupationController.getAllOccupation] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }



    @GetMapping("/occupation")
    public ResponseEntity<?> getOccupationById(@RequestParam(value = "occupationId",required = true) Integer occupationId, @RequestParam(value = "langId",required = true)  Integer langId) throws ResourceNotFoundException {
        log.info("OccupationController :getOccupationById by occupationId {}",occupationId);
        Occupation occupation = occupationService.findByOccupationId(occupationId,langId).orElseThrow(() -> new ResourceNotFoundException("Resource not found for this id :: " + occupationId));
        log.info("[OccupationController.getAllOccupation] : success response");
        return ResponseEntity.ok().body(occupation);
    }
}
