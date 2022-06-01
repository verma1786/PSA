package com.state.psa.master.controller;
import com.state.psa.master.common.constants.Constants;
import com.state.psa.master.common.response.ApiResponse;
import com.state.psa.master.exception.ResourceNotFoundException;
import com.state.psa.master.model.entity.Gender;
import com.state.psa.master.model.entity.Relation;
import com.state.psa.master.model.response.Response;
import com.state.psa.master.service.GenderService;
import com.state.psa.master.service.RelationService;
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
public class RelationController {
    @Autowired
    private RelationService relationService;

    @GetMapping("/relations")
    private ResponseEntity<ApiResponse> getGenders() {
        log.info("RelationController :getRelations");
        List<Relation> relations=  relationService.findAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new Response<>(relations))
                .build();
        log.info("[RelationController.getRelations] : success response");
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/relations/{id}")
    public ResponseEntity<ApiResponse> getRelation(@PathVariable(value = "id") Integer genderId) throws Throwable {
        log.info("RelationController.getRelation request genderId :: {}",genderId);
        Relation relation = (Relation) relationService.findById(genderId).orElseThrow(() -> new ResourceNotFoundException("Relation not found for this id :: " + genderId));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(relation)
                .build();
        log.info("[RelationController.getRelation] : success response :: {}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }

}
