package com.state.psa.master.controller;


import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.dto.response.ApiResponse;
import com.state.psa.master.model.dto.response.StateResponse;
import com.state.psa.master.model.entity.State;
import com.state.psa.master.service.StateService;
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
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping("/states")
    public ResponseEntity<ApiResponse> getAllStateDetails() {
        log.info("StateController :getAllStateDetails");
        List<State> states=  stateService.findAll().stream()
                .filter(e -> "C".equalsIgnoreCase(e.getStatus()) && e.getState().length() > 1)
                .collect(Collectors.toList());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new StateResponse(states))
                .build();
        log.info("[StateController.getAllStateDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
    @GetMapping("/allStates")
    private List allStates() {
        List<State> states=  stateService.findAll().stream()
                .filter(e -> "C".equalsIgnoreCase(e.getStatus()) && e.getState().length() > 1)
                .collect(Collectors.toList());
        return states;
    }


    @GetMapping("/states/{id}")
    public ResponseEntity<State> getStateById(@PathVariable(value = "id") Integer stateId) throws ResourceNotFoundException {
        State state = (State) stateService.findById(stateId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + stateId));
        return ResponseEntity.ok().body(state);
    }
}
