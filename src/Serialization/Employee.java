package Serialization;

import java.io.Serializable;

public class Employee implements Serializable{
    public static final long serialVersionUID = 1L;
    String name;
    int age;
    int salary;
    String job;

    public Employee(String name, int age, int salary, String job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}
