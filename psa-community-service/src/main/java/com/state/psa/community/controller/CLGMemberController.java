package com.state.psa.community.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.state.psa.community.exception.JsonProcessingException;
import com.state.psa.community.model.dto.request.CLGMemberRequest;
import com.state.psa.community.model.dto.response.ApiResponse;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.service.CLGMemberService;
import com.state.psa.community.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping("/psa-community")
public class CLGMemberController {
    @Autowired
    private CLGMemberService clgMemberService;

    @RequestMapping(path = "/saveFormData", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponse> saveCLGMember(@RequestParam(value = "personName", required = true)  String personName,  @RequestParam(value = "file", required = true) MultipartFile file) {
    log.info("CLGMemberController :saveCLGMemberDetails request {} ", personName);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .build();
        log.info("[CLGMeetingController.saveCLGMeetingDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }


        @RequestMapping(path = "/saveMemberData", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
        public ResponseEntity<ApiResponse> saveCLGMember1(@RequestPart(value = "clgMemberData", required = true)  String clgMemberData, @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {
        log.info("CLGMemberController :saveCLGMemberDetails request {} ", clgMemberData);
        ObjectMapper objectMapper= new ObjectMapper();
        CLGMemberDetails clgMemberDetails=null;
        try {
            clgMemberDetails=objectMapper.readValue(clgMemberData,CLGMemberDetails.class);
        } catch (JsonProcessingException e) {
            throw new JsonProcessingException(e.getMessage());
        }
        Long id= clgMemberService.saveMemberDetailWithFile(clgMemberDetails,file);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(id)
                .build();
        log.info("[CLGMeetingController.saveCLGMeetingDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<ApiResponse>  editMemberDetails(@PathVariable("id") Long id) {
        log.info("CLGMemberController :editMemberDetails request id {} ", id);
        CLGMemberDetails clgMemberDetails=  clgMemberService.findById(id);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(clgMemberDetails)
                .build();
        log.info("[CLGMemberController.getMemberDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
    @GetMapping("/getAllMembers")
    public ResponseEntity<ApiResponse>  getAllMemberDetails(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
        log.info("CLGMemberController :getMemberDetails pageNo {} pageSize {} sortBy {} " ,pageNo,pageSize,sortBy);
        List<CLGMemberDetails> clgMemberDetails=  clgMemberService.getAllMemberDetails(pageNo,pageSize,sortBy);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(clgMemberDetails)
                .build();
        log.info("[CLGMemberController.getAllMemberDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
   @RequestMapping(path = "/updateMemberData", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
   public ResponseEntity<ApiResponse> updateCLGMember(@RequestPart(value = "id", required = true)  String id,@RequestPart(value = "clgMemberData", required = true)  String clgMemberData, @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {
       log.info("CLGMemberController :updateCLGMember request {} ", clgMemberData);
       ObjectMapper objectMapper= new ObjectMapper();
       CLGMemberDetails memberRequest=null;
       try {
           memberRequest=objectMapper.readValue(clgMemberData,CLGMemberDetails.class);
       } catch (JsonProcessingException e) {
           throw new JsonProcessingException(e.getMessage());
       }
       CLGMemberDetails detail = clgMemberService.findById(Long.parseLong(id));
        if (detail!=null) {
            if (detail.getCity() != null)
                detail.setCity(memberRequest.getCity());
            if (detail.getPersonName() != null)
                detail.setPersonName(memberRequest.getPersonName());
            if (detail.getAddress() != null)
                detail.setAddress(memberRequest.getAddress());
            if (detail.getStateId() != null)
                detail.setStateId(memberRequest.getStateId());
            if (detail.getState() != null)
                detail.setState(memberRequest.getState());
            if (detail.getDetails() != null)
                detail.setDetails(memberRequest.getDetails());
            if (detail.getBeatId() != null)
                detail.setBeatId(memberRequest.getBeatId());
            if (detail.getBeat() != null)
                detail.setBeat(memberRequest.getBeatId());
            if (detail.getDistrictId() != null)
                detail.setDistrictId(memberRequest.getDistrictId());
            if (detail.getDistrict() != null)
                detail.setDistrict(memberRequest.getDistrict());
            Long mId= clgMemberService.updateMemberDetailWithFile(detail,file);

            ApiResponse apiResponse = ApiResponse.builder()
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .message(Constants.SUCCESS)
                    .data(mId)
                    .build();
                 return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMemberDetail/{id}")
    public ResponseEntity<ApiResponse> deleteMemberDetail(@PathVariable("id") long id) {
        log.info("CLGMemberController :deleteMemberDetail id {} ", id);
        clgMemberService.deleteMember(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.DELETE_SUCCESS_MSG+id)
                .build();
        log.info("[CLGMeetingController.deleteMemberDetail] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }
}
