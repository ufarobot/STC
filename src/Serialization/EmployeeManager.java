package Serialization;

import java.io.*;
import java.util.ArrayList;

public class EmployeeManager {
    private final String fileName;
    private ArrayList<Employee> employees;

    public EmployeeManager(String fileName) {
        this.fileName = fileName;
        employees = new ArrayList<>();
    }

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager("manager1.txt");
        Employee employee = new Employee("Ivan", 21, 1000, "manager");
        manager.save(employee);
        manager.displayEmployee(manager.getByName("Ivan"));
        for (Employee emp : manager.getByJob("manager"))
            manager.displayEmployee(emp);
    }

    public Iterable<Employee> getByJob(String job) {
        ArrayList<Employee> resultList = new ArrayList<>();
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName));
            employees = (ArrayList<Employee>) file.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Employee employee : employees)
            if (employee.name.equals(job))
                resultList.add(employee);
        return resultList;
    }

    private void displayEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("No such employee");
            return;
        }
        System.out.println(employee.toString());
    }

    public Employee getByName(String name) {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName));
            employees = (ArrayList<Employee>) file.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Employee employee : employees)
            if (employee.name.equals(name))
                return employee;
        return null;
    }

    public void save(Employee employee) {
        add(employee);
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName));
            file.writeObject(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(Employee employee) {
        if (employee == null) throw new IllegalArgumentException();
        employees.add(employee);
    }
}
