package com.state.psa.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.state.psa.community.model.dto.request.CLGMemberRequest;
import com.state.psa.community.model.dto.request.CLGMemberResponse;
import com.state.psa.community.model.dto.response.ApiResponse;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.model.entity.Person;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping("/psa-community")
public class CLGMemberController {
    @Autowired
    CLGMemberService clgMemberService;

//    @PostMapping("/save")
//    public ResponseEntity<ApiResponse> saveCLGMemberDetails(@RequestBody CLGMemberDetails clgMemberDetails) {
//     log.info("CLGMemberController :saveCLGMemberDetails request {} ", clgMemberDetails);
//        clgMemberService.save(clgMemberDetails);
//        ApiResponse apiResponse = ApiResponse.builder()
//                .status(String.valueOf(HttpStatus.OK.value()))
//                .message(Constants.SUCCESS)
//                .build();
//        log.info("[CLGMeetingController.saveCLGMeetingDetails] : success response");
//        return ResponseEntity.ok().body(apiResponse);
//    }
    @RequestMapping(path = "/saveFormData", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponse> saveCLGMember(@RequestParam(value = "personName", required = true)  String personName,
                                                     @RequestParam(value = "file", required = true) MultipartFile file) {
    log.info("CLGMemberController :saveCLGMemberDetails request {} ", personName);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .build();
        log.info("[CLGMeetingController.saveCLGMeetingDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }



           @PostMapping("/savePerson")
           public ResponseEntity<String> savePerson(@RequestBody Person person ) {
               log.info("CLGMemberController :savePerson request {} ", person.toString());
               return ResponseEntity.ok().body("Data save successfully.");
           }



        @RequestMapping(path = "/saveMemberData", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
                MediaType.MULTIPART_FORM_DATA_VALUE })
        public ResponseEntity<ApiResponse> saveCLGMember1(@RequestPart(value = "clgMemberData", required = true)  String clgMemberData,
                                                         @RequestPart(value = "file", required = true) MultipartFile file) {
        log.info("CLGMemberController :saveCLGMemberDetails request {} ", clgMemberData);
        ObjectMapper objectMapper= new ObjectMapper();
        CLGMemberDetails clgMemberDetails=null;
        try {
            clgMemberDetails=objectMapper.readValue(clgMemberData,CLGMemberDetails.class);
        } catch (JsonProcessingException e) {
          e.getLocalizedMessage();
        }
        clgMemberService.saveMemberDetailWithFile(clgMemberDetails,file);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data("API")
                .build();
        log.info("[CLGMeetingController.saveCLGMeetingDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<ApiResponse>  getMemberDetails(@PathVariable("id") Long id) {
        log.info("CLGMemberController :getMemberDetails request id {} ", id);
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
        log.info("CLGMemberController :getMemberDetails ");
        List<CLGMemberDetails> clgMemberDetails=  clgMemberService.getAllMemberDetails(pageNo,pageSize,sortBy);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(clgMemberDetails)
                .build();
        log.info("[CLGMemberController.getAllMemberDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTutorial(@PathVariable("id") @NotNull(message="Id should not be blank.") long id, @RequestBody @Valid  CLGMemberRequest memberRequest) {
        Optional<CLGMemberDetails> memberDetail = Optional.ofNullable(clgMemberService.findById(id));
        if (memberDetail.isPresent()) {
            CLGMemberDetails clgMemberDetail =memberDetail.get();
            clgMemberDetail.setAddress(memberRequest.getAddress());
            clgMemberDetail.setState(memberRequest.getState());
            clgMemberDetail.setDetails(memberRequest.getDetails());
            clgMemberService.save(clgMemberDetail);

            ApiResponse apiResponse = ApiResponse.builder()
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .message(Constants.SUCCESS)
                    .data(clgMemberDetail)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/updateMemberDetail/{id}")
    public ResponseEntity<ApiResponse> updateMemberDetail(@RequestBody CLGMemberRequest memberRequest) {
        log.info("CLGMemberController :updateMemberDetail request {} ", memberRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .build();
        log.info("[CLGMeetingController.saveCLGMeetingDetails] : success response");
        return ResponseEntity.ok().body(apiResponse);
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
