package com.state.psa.community.controller;


import com.state.psa.community.model.dto.response.ApiResponse;
import com.state.psa.community.model.entity.CLGMemberType;
import com.state.psa.community.service.CLGMemberTypeService;
import com.state.psa.community.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-community")
public class CLGMemberTypeController {
    @Autowired
    CLGMemberTypeService clgMemberTypeService;

    @GetMapping("/clgMemberType")
    public ResponseEntity<ApiResponse> getAllMemberTypes() {
        log.info("CLGMemberTypeController :getAllMemberTypes ");
        List<CLGMemberType> clgMemberTypeList=  clgMemberTypeService.findAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(clgMemberTypeList)
                .build();
        log.info("[CLGMemberTypeController.getAllMemberTypes] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
}
