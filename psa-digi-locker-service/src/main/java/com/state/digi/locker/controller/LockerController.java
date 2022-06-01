package com.state.digi.locker.controller;

import com.state.digi.locker.model.Locker;
import com.state.digi.locker.service.LockerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/digi-locker")
public class LockerController {
    @Autowired
    private LockerService lockerService;

    @PostMapping(path = "/storeAadhaar")
    public ResponseEntity<String> storeAadhaar(@RequestParam(value = "id", required = true) Locker locker) {
        log.info("LockerController :storeAadhaar request {} ", locker);
        String refId=lockerService.storeAadhaar(locker.getAadhaarId());
        log.info("[LockerController.storeAadhaar] : success response ref {} ",refId);
        return ResponseEntity.ok().body(refId);
    }
    @GetMapping("/getAadhaar/{id}")
    public ResponseEntity<?> getAadhaarId(@PathVariable("id") String id) {
        log.info("LockerController :getAadhaarId request id {} ", id);
        String aadhaarId = lockerService.getAadhaar(id);
        log.info("[LockerController.getAadhaarId] : success response");
        return ResponseEntity.ok().body(aadhaarId);

    }

}
