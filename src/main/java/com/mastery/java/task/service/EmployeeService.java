package com.mastery.java.task.service;

import com.mastery.java.task.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findEmployeeById(int id);

    void createEmployee(Employee employee);

    void updateEmployee(int id, Employee employee);

    void deleteEmployee(int id);

}
