package com.state.psa.master.controller;


import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.dto.response.ApiResponse;
import com.state.psa.master.model.dto.response.StateResponse;
import com.state.psa.master.model.entity.Beat;
import com.state.psa.master.service.BeatService;
import com.state.psa.master.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

@NoArgsConstructor
@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class BeatController {
    @Autowired
    BeatService beatService;

    @GetMapping("/beats/{psId}/{langId}")
    public ResponseEntity<ApiResponse> getAllBeatDetails(@PathVariable("psId") Integer psId,@PathVariable("langId") Integer langId) {
        log.info("BeatController :getAllBeatDetails");
        List<Beat> beats=  beatService.findAll(psId,langId).stream()
                .filter(e -> "C".equalsIgnoreCase(e.getStatus()) && e.getBeatName().length() > 1)
                .collect(Collectors.toList());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new StateResponse(beats))
                .build();
        log.info("[BeatController.getAllBeatDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }



    @GetMapping("/beat/{id}")
    public ResponseEntity<?> getBeatById(@PathVariable(value = "id") Integer psId,@PathVariable("langId") Integer langId) throws ResourceNotFoundException {
        log.info("BeatController :getBeatById from psId {}",psId);
        Beat beat = beatService.findByBeatId(psId,langId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + psId));
        log.info("[BeatController.getBeatById ] : success response");
        return ResponseEntity.ok().body(beat);
    }
}
