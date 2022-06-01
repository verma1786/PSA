package com.state.psa.master.controller;

import com.state.psa.master.common.constants.Constants;
import com.state.psa.master.common.response.ApiResponse;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.entity.Gender;
import com.state.psa.master.model.response.Response;
import com.state.psa.master.service.GenderService;
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
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping("/genders")
    private ResponseEntity<ApiResponse> getGenders() {
        log.info("GenderController :getGenders");
        List<Gender> genders=  genderService.findAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new Response<>(genders))
                .build();
        log.info("[GenderController.getGenders] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/genders/{id}")
    public ResponseEntity<ApiResponse> getGender(@PathVariable(value = "id") Integer genderId) throws Throwable {
        log.info("GenderController :getGender request genderId :: {}",genderId);
        Gender gender = (Gender) genderService.findById(genderId).orElseThrow(() -> new ResourceNotFoundException("Gender not found for this id :: " + genderId));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(gender)
                .build();
        log.info("[GenderController.getGender] : success response :: {}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }
}
