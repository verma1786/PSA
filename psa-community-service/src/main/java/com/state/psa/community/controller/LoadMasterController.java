package com.state.psa.community.controller;


import com.state.psa.community.model.State;
import com.state.psa.community.model.dto.response.ApiResponse;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.service.CLGMemberService;
import com.state.psa.community.service.LoadMasterService;
import com.state.psa.community.utils.Constants;
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
@RequestMapping("/psa-community")
public class LoadMasterController {
    @Autowired
    LoadMasterService loadMasterService;

    @GetMapping("/get-state")
    public ResponseEntity<ApiResponse> getState() {
        log.info("LoadMasterController :getState request id. ");
        State state=  loadMasterService.findById(27);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(state)
                .build();
        log.info("[LoadMasterController.getState] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
    @GetMapping("/get-members")
    public ResponseEntity<ApiResponse> findAllCLGMemberDetails() {
        log.info("LoadMasterController :getCLGMembers. ");
        List<CLGMemberDetails> clgMemberDetails =  loadMasterService.findAllCLGMemberDetails();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(clgMemberDetails)
                .build();
        log.info("[LoadMasterController.getCLGMembers] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }

}
