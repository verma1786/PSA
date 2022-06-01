package com.state.psa.master.controller;

import com.state.psa.master.common.constants.Constants;
import com.state.psa.master.common.response.ApiResponse;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.response.Response;
import com.state.psa.master.service.StateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.state.psa.master.model.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping("/states")
    private ResponseEntity<ApiResponse> getStates() {
        log.info("StateController :getStates");
        List<State> states=  stateService.findAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new Response<>(states))
                .build();
        log.info("[StateController.getStates] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/states/{id}")
    public ResponseEntity<ApiResponse> getState(@PathVariable(value = "id") Integer stateId) throws Throwable {
        log.info("StateController :getState request stateId {}",stateId);
        Optional<State> state = Optional.ofNullable(stateService.findById(stateId).orElseThrow(() -> new ResourceNotFoundException("State not found for this id :: " + stateId)));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(state)
                .build();
        log.info("[StateController.getState] : success response :: {}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }
}
