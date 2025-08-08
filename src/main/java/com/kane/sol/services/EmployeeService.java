package com.kane.sol.services;

import com.kane.sol.entities.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee findByCredentials(String name, String password);
    void saveEmployee(Employee employee);
    void deleteEmployee(Long id);
}

