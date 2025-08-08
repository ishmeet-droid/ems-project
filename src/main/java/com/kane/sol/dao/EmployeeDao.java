package com.kane.sol.dao;

import com.kane.sol.entities.Employee;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    List<Employee> search(String keyword);
    Employee findById(Long id);
    Employee findByCredentials(String name, String password);
    void save(Employee e);
    void delete(Employee e);
}