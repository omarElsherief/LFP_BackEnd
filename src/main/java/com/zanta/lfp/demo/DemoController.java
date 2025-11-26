package com.zanta.lfp.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class DemoController {
    @GetMapping("/demo-controller")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }
    @PostMapping("/admin/demo-controller")
    public ResponseEntity<String> sayHelloAdmin(){
        return ResponseEntity.ok("Hello from admin secured endpoint");
    }
}
