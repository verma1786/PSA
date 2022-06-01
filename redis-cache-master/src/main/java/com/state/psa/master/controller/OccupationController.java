package com.state.psa.master.controller;
import com.state.psa.master.common.constants.Constants;
import com.state.psa.master.common.response.ApiResponse;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.entity.Occupation;
import com.state.psa.master.model.entity.Relation;
import com.state.psa.master.model.response.Response;
import com.state.psa.master.service.RelationService;
import com.state.psa.master.service.impl.OccupationServiceImpl;
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
public class OccupationController {

    @Autowired
    private OccupationServiceImpl occupationService;

    @GetMapping("/occupations")
    private ResponseEntity<ApiResponse> getOccupations() {
        log.info("OccupationController :getOccupations");
        List<Occupation> relations=  occupationService.findAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new Response<>(relations))
                .build();
        log.info("[OccupationController.getOccupations] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/occupations/{id}")
    public ResponseEntity<ApiResponse> getOccupation(@PathVariable(value = "id") Integer occupationId) throws Throwable {
        log.info("OccupationController.getOccupation request genderId :: {}",occupationId);
        Occupation occupation = (Occupation) occupationService.findById(occupationId).orElseThrow(() -> new ResourceNotFoundException("Relation not found for this id :: " + occupationId));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(occupation)
                .build();
        log.info("[OccupationController.etOccupation] : success response :: {}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }


}
