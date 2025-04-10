package com.example.demo.annotations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@Slf4j
public class SpController {

    private final MyService myService;

    @GetMapping
    public ResponseEntity<Employee> demo() {
        String name = "SpController11";
        String method = "demo";
        List<Employee> employee = List.of(new Employee("Dima", 12, 21.2),
                new Employee("Artem", 13, 221.1));
        return ResponseEntity.ok(myService.demo(name, method, employee));
    }
}
