package com.trustbycode.securitytoks.Controller.ControlDemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/au/control-demo")
public class Controller {
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}
