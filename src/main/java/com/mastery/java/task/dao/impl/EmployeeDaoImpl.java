package com.mastery.java.task.dao.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.mapper.EmployeeRowMapper;
import com.mastery.java.task.model.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from employee", new EmployeeRowMapper());
    }

    @Override
    public Optional<Employee> findEmployeeById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from employee where employee_id = ?", new EmployeeRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into employee(first_name, last_name, department_id, job_title, gender, date_of_birth) values (?, ?, ?, ?, CAST(? AS gender), ?)",
                    new String[]{"employee_id"});
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getDepartmentId());
            ps.setString(4, employee.getJobTitle());
            ps.setString(5, employee.getGender().toString());
            ps.setObject(6, employee.getDateOfBirth());
            return ps;
        }, keyHolder);
        employee.setEmployeeId(keyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        jdbcTemplate.update(
                "update employee set first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender = ?::gender, date_of_birth = ? where employee_id = ?",
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(), employee.getGender().name(), employee.getDateOfBirth(), id);
        employee.setEmployeeId(id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        jdbcTemplate.update("delete from employee where employee_id = ?", id);
    }
}
