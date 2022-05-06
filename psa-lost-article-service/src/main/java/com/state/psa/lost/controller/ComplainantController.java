package com.state.psa.lost.controller;

import com.state.psa.lost.common.constants.Constants;
import com.state.psa.lost.common.exception.ResourceNotFoundException;
import com.state.psa.lost.common.exception.ValidJson;
import com.state.psa.lost.common.response.ApiResponse;
import com.state.psa.lost.model.ComplainantDetail;
import com.state.psa.lost.service.IComplainantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.state.psa.lost.SchemaLocations.COMPLAINANT;
import java.util.List;

/**
 * created by : vinay kumar
 * date : 04-05-2022
 * complainant with lost article details
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/psa-lost")
public class ComplainantController {
    @Autowired
    private IComplainantService complainantService;

    /**
     * get all complainant with lost article details
     */

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse>  findAllComplainant(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
        log.info("ComplainantController :findAllComplainant pageNo {} pageSize {} sortBy {} " ,pageNo,pageSize,sortBy);
        List<ComplainantDetail> complainantDetails=  complainantService.findAll(pageNo,pageSize,sortBy);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(complainantDetails)
                .build();
        log.info("[ComplainantController.findAllComplainant] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }

    /**
     * get complainant details with lost article by compId
     */

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse>  getComplainantById(@PathVariable(value = "id") Long compId) {
        log.info("ComplainantController: getComplainantById compId {}",compId);
        ComplainantDetail complainantDetail=  complainantService.findById(compId).orElseThrow(() -> new ResourceNotFoundException("ComplainantDetail", "id", compId));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(complainantDetail)
                .build();
        log.info("ComplainantController: getComplainantById complainantDetail response");
        return ResponseEntity.ok().body(apiResponse);
    }

    /**
     * save complainant details along lost articles
     */

    @PostMapping("/save")
    public ResponseEntity<ApiResponse>  createComplainantDetail(@ValidJson(COMPLAINANT) ComplainantDetail complainantDetail) {
        log.info("ComplainantController: createComplainantDetail ComplainantDetail {}",complainantDetail);
        ComplainantDetail compDetail= complainantService.save(complainantDetail);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(compDetail)
                .build();
        log.info("ComplainantController: createComplainantDetail complainantDetail response");
        return ResponseEntity.ok().body(apiResponse);
    }

    /**
     * update complainant details along lost articles
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse>  updateComplainantDetail(@PathVariable(value = "id") Long compId, @RequestBody ComplainantDetail complainantDetail) {
        log.info("ComplainantController: updateComplainantDetail ComplainantDetail {}",complainantDetail);
        ComplainantDetail updatedComplainantDetail = complainantService.updateComplainantDetail(compId,complainantDetail);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(updatedComplainantDetail)
                .build();
        log.info("ComplainantController: updateComplainantDetail response");
        return ResponseEntity.ok().body(apiResponse);
    }

    /**
     * delete complainant details along lost articles
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComplainantDetail(@PathVariable(value = "id") Long complainantId) {
        log.info("ComplainantController: deleteComplainantDetail complainantId {}",complainantId);
         complainantService.deleteComplainantDetail(complainantId);
        log.info("ComplainantController: deleteComplainantDetail response");
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
