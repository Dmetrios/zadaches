package com.example.demo.annotations;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profiling
public class MyServiceImpl implements MyService {

    private final ClassicRepository repository;

    public MyServiceImpl(ClassicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee demo(String name, String method, List<Employee> employee) {
        System.out.println(name + " " + method);
        employee.get(0).method();
        privat();
        repository.voi();
        return employee.get(0);
    }

    private void privat(){
        System.out.println("Hello World");
    }
}
