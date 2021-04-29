package com.mastery.java.task.dao;

import com.mastery.java.task.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();

    Employee findEmployeeById(int id);

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

}
