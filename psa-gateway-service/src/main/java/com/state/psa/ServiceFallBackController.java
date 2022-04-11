package com.state.psa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceFallBackController {
    @GetMapping("masterService")
    public String asterService() {
        return "master-service is down...";
    }

    @GetMapping("communityService")
    public String communityService() {
        return "community-service is down...";
    }

    @GetMapping("fileService")
    public String fileService() {
        return "file-service is down...";
    }
}
