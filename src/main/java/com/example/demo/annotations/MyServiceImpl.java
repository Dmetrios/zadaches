package com.example.demo;

import org.springframework.stereotype.Service;

@Service
@Profiling
public class MyServiceImpl implements MyService {

    public MyServiceImpl() {
    }

    @Override
    public void demo() {
        System.out.println("demo");
    }
}
