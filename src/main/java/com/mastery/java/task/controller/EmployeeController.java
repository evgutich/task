package com.mastery.java.task.controller;

import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }


    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
        employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
