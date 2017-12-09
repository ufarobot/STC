package Serialization;

import java.io.*;
import java.util.ArrayList;

public class EmployeeManager {
    private final String fileName;

    public EmployeeManager(String fileName) {
        this.fileName = fileName;

        // creates new file, if doesn't exists or delete old data from existing file
        prepareFile(fileName);
    }

    private void prepareFile(String fileName) {
        // TODO is there more efficient implementation?
        try {
            new PrintWriter(fileName).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Employee delete(String name) {
        ArrayList<Employee> employees = getEmployees();
        for (Employee employee : employees)
            if (employee.name.equals(name)) {
                employees.remove(employee);
                writeEmployeesToFile(employees);
                return employee;
            }
        return null;
    }

    private ArrayList<Employee> getEmployees() {
        try(ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName))) {
            return  (ArrayList<Employee>) file.readObject();
        } catch (EOFException e){ // If file is empty, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Iterable<Employee> getByJob(String job) {
        ArrayList<Employee> employees = getEmployees();
        ArrayList<Employee> resultList = new ArrayList<>();
        for (Employee employee : employees)
            if (employee.job.equals(job))
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
        ArrayList<Employee> employees = getEmployees();
        for (Employee employee : employees)
            if (employee.name.equals(name))
                return employee;
        return null;
    }

    public void save(Employee employee) {
        if (employee == null) throw new IllegalArgumentException();
        ArrayList<Employee> employees = getEmployees();
        employees.add(employee);
        writeEmployeesToFile(employees);
    }

    private void writeEmployeesToFile(ArrayList<Employee> employees) {
        try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName))) {
            file.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Unit testing
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager("manager1.txt");

        Employee employee1 = new Employee("Ivan", 21, 1000, "manager");
        Employee employee2 = new Employee("Petr", 22, 2000, "manager");
        Employee employee3 = new Employee("John", 23, 1000, "developer");

        manager.save(employee1);
        manager.save(employee2);
        manager.save(employee3);

        manager.displayEmployee(manager.getByName("Petr"));

        System.out.println("managers------------------------");
        for (Employee emp : manager.getByJob("manager"))
            manager.displayEmployee(emp);

        manager.delete("Petr");
        manager.displayEmployee(manager.getByName("Petr"));
        System.out.println("managers ------------------------");
        for (Employee emp : manager.getByJob("manager"))
            manager.displayEmployee(emp);
    }
}
