package com.mastery.java.task.service;

import com.mastery.java.task.dao.impl.EmployeeDaoImpl;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.model.Gender;
import com.mastery.java.task.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private EmployeeDaoImpl dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllEmployeesTest() {
        List<Employee> expectedEmployeeList = new ArrayList<>();
        Employee empOne = new Employee(1, "Ivan", "Ivanov", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));
        Employee empTwo = new Employee(2, "Elena", "Petrova", 2, "hr", Gender.FEMALE, LocalDate.of(1990, 2, 22));

        expectedEmployeeList.add(empOne);
        expectedEmployeeList.add(empTwo);

        when(dao.findAll()).thenReturn(expectedEmployeeList);

        List<Employee> employeeList = service.findAll();

        assertEquals(expectedEmployeeList, employeeList);
        verify(dao).findAll();
    }

    @Test
    public void findEmployeeByIdTest() {
        Employee expectedEmployee = new Employee(1, "Ivan", "Ivanov", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));

        when(dao.findEmployeeById(1)).thenReturn(Optional.of(expectedEmployee));
        Employee employee = service.findEmployeeById(1);

        assertEquals(expectedEmployee, employee);
        verify(dao).findEmployeeById(1);
    }

    @Test
    public void findEmployeeByIdExceptionTest() {
        when(dao.findEmployeeById(1)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployeeById(1));
        verify(dao).findEmployeeById(1);
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
    public void deleteEmployeeTest() {
        Employee emp = new Employee(1, "Ivan", "Ivan", 1, "developer", Gender.MALE, LocalDate.of(1990, 1, 12));
        service.deleteEmployee(emp);
        verify(dao).deleteEmployee(emp);
    }
}
