package com.example.demo.annotations;

public class Employee {

    private String name;

    private int age;

    private double salary;

    public Employee() {
    }

    public void method() {
        System.out.println(name + " " + age + " " + salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public Employee(String name, int age, double salary) {

        this.name = name;

        this.age = age;

        this.salary = salary;

    }

    public String getName() {

        return name;

    }

    public int getAge() {

        return age;

    }

    public double getSalary() {
        return salary;
    }
}
