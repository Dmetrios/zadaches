package com.example.demo.annotations;

import org.springframework.stereotype.Repository;

@Repository
@Profiling
public class ClassicRepository {

    public void voi(){
        System.out.println("hello");
    }
}
