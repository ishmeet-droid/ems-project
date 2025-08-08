package com.kane.sol.services;

import com.kane.sol.entities.Employee;

public interface LoginService {
    Employee validate(String username, String password);

}
