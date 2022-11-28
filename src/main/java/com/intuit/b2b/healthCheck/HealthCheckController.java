package com.intuit.b2b.healthCheck;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public ResponseEntity<String> index() {
        return ResponseEntity.status(HttpStatus.OK).body("0");
    }

}