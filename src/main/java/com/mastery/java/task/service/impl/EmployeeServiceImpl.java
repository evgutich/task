package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeDao.findEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee existingEmployee = employeeDao.findEmployeeById(id).orElseThrow((() -> new EmployeeNotFoundException(id)));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        return employeeDao.updateEmployee(id, employee);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee existingEmployee = employeeDao.findEmployeeById(id).orElseThrow((() -> new EmployeeNotFoundException(id)));
        employeeDao.deleteEmployee(existingEmployee.getEmployeeId());
    }
}
