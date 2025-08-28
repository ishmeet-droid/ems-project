package com.kane.sol.services;

import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;

public interface LoginService {
    EmployeeDto validate(String username, String password);

    EmployeeDto validateUserName(String username);
}
