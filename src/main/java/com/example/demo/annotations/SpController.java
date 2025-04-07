package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@Slf4j
public class SpController {

    private final MyService myService;

    @GetMapping
    public void demo() {
        myService.demo();
    }
}
