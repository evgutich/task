package com.mastery.java.task.service;

import com.mastery.java.task.dao.impl.EmployeeDaoImpl;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.model.Gender;
import com.mastery.java.task.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl service;

    @Mock
    EmployeeDaoImpl dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllEmployeesTest() {
        List<Employee> employees = new ArrayList<>();
        Employee empOne = new Employee(1, "Ivan", "Ivanov", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));
        Employee empTwo = new Employee(2, "Elena", "Petrova", 2, "hr", Gender.FEMALE, LocalDate.of(1990, 2, 22));

        employees.add(empOne);
        employees.add(empTwo);

        when(dao.findAll()).thenReturn(employees);

        List<Employee> employeeList = service.findAll();

        assertEquals(2, employeeList.size());
        verify(dao).findAll();
    }

    @Test
    public void findEmployeeByIdTest() {
        Employee expectedEmployee = new Employee(1, "Ivan", "Ivanov", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));

        when(dao.findEmployeeById(1)).thenReturn(Optional.of(expectedEmployee));
        Employee employee = service.findEmployeeById(1);

        assertEquals(expectedEmployee, employee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void findEmployeeByIdExceptionTest() {
        when(dao.findEmployeeById(1)).thenReturn(Optional.empty());
        when(service.findEmployeeById(1)).thenThrow(new EmployeeNotFoundException());
    }

    @Test
    public void createEmployeeTest() {
        Employee emp = new Employee(1, "Ivan", "Ivanov", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));
        service.createEmployee(emp);
        verify(dao).createEmployee(emp);
    }

    @Test
    public void updateEmployeeTest() {
        Employee emp = new Employee(1, "Ivan", "Ivan", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));
        service.updateEmployee(emp);
        verify(dao).updateEmployee(emp);
    }

    @Test
    public void deleteEmployeeTest(){
        Employee emp = new Employee(1, "Ivan", "Ivan", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));
        service.deleteEmployee(emp);
        verify(dao).deleteEmployee(emp);
    }
}