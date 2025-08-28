package com.kane.sol.services;

import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto findByCredentials(String name, String password);
    void saveEmployee(EmployeeDto employee);
    void deleteEmployee(Long id);
    void promoteToManager(Long id);
    EmployeeDto getEmployeeByUserName(String userName);
    List<String> suggestEmployeeNames(String startsWith);
}

