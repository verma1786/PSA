package com.state.psa.master.controller;

import com.state.psa.master.model.dto.response.ApiResponse;
import com.state.psa.master.model.dto.response.RelationTypeResponse;
import com.state.psa.master.model.entity.RelationType;
import com.state.psa.master.service.RelationTypeService;
import com.state.psa.master.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/psa-master")
public class RelationTypeController {
    @Autowired
    private RelationTypeService relationTypeService;

    @GetMapping("/relation-type")
    public ResponseEntity<ApiResponse> getAllRelationTypeByLangId(@RequestParam Integer langId) {
        log.info("RelationTypeController :getAllRelationTypeByLangId request id {} ", langId);
        List<RelationType> filteredList = relationTypeService.findByLangIdOrderByPsAsc(langId).stream()
                .filter(e -> "C".equalsIgnoreCase(e.getStatus()))
                .collect(Collectors.toList());

        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(Constants.SUCCESS)
                .data(new RelationTypeResponse(filteredList))
                .build();
        log.info("[RelationTypeController.getAllRelationTypeByLangId] : success response");
        return ResponseEntity.ok().body(apiResponse);

    }
}
