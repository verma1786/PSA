package com.state.psa.master.controller;


import com.state.psa.master.model.dto.response.ApiResponse;
import com.state.psa.master.model.entity.Nationality;
import com.state.psa.master.service.NationalityService;
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

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class NationalityController {
    @Autowired
    NationalityService nationalityService;

    @GetMapping("/nationality/{id}")
    public ResponseEntity<ApiResponse> getNationalityDetailsById(@PathVariable("id") Integer id) {
        log.info("NationalityController :getNationalityDetailsById request id {} ", id);
        Nationality nationality=  nationalityService.findById(id).get();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(nationality)
                .build();
        log.info("[NationalityController.getNationalityDetailsById] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
}
