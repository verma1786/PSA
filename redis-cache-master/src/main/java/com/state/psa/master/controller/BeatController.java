package com.state.psa.master.controller;

import com.state.psa.master.common.constants.Constants;
import com.state.psa.master.common.response.ApiResponse;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.entity.Beat;
import com.state.psa.master.model.response.Response;
import com.state.psa.master.service.BeatService;
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
public class BeatController {
    @Autowired
    private BeatService beatService;

    @GetMapping("/beats/{id}")
    private ResponseEntity<ApiResponse> getBeats(@PathVariable(value = "id") Integer psId) {
        log.info("[BeatController.getBeats] request psId :: {}",psId);
        List<Beat> beats=  beatService.findAll(psId);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new Response<>(beats))
                .build();
        log.info("[BeatController.getBeats] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/beat/{id}")
    public ResponseEntity<ApiResponse> getBeat(@PathVariable(value = "id") Integer beatId) throws Throwable {
        log.info("[BeatController.getBeat] request genderId :: {}",beatId);
        Beat beat = (Beat) beatService.findById(beatId).orElseThrow(() -> new ResourceNotFoundException("Beat not found for this id :: " + beatId));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(beat)
                .build();
        log.info("[BeatController.getBeat] : success response :: {}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }

}
