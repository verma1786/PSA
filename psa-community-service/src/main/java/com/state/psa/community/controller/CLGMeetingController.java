package com.state.psa.community.controller;

import com.state.psa.community.model.dto.response.ApiResponse;
import com.state.psa.community.model.entity.CLGMeetingDetails;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.service.CLGMeetingService;
import com.state.psa.community.service.CLGMemberService;
import com.state.psa.community.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-community")
public class CLGMeetingController {

    @Autowired
    CLGMeetingService clgMeetingService;

    @PostMapping("/meeting/save")
    public ResponseEntity<ApiResponse> saveCLGMeetingDetails(@RequestBody CLGMeetingDetails clgMeetingDetails) {
        log.info("CLGMeetingController :saveCLGMeetingDetails request {} ", clgMeetingDetails);
        Long id=clgMeetingService.save(clgMeetingDetails);
        ApiResponse apiResponse=null;

             apiResponse = ApiResponse.builder()
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .message(Constants.SUCCESS)
                    .data(id)
                    .build();
            log.info("[CLGMeetingController.saveCLGMeetingDetails] : success response");

        return ResponseEntity.ok().body(apiResponse);
    }


}
