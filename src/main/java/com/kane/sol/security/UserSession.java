package com.kane.sol.security;

import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;
import com.kane.sol.entities.Role;

public class UserSession {
    private EmployeeDto currentUser;
    public EmployeeDto getCurrentUser() { return currentUser; }
    public void setCurrentUser(EmployeeDto e) { this.currentUser = e; }
    public boolean isAdmin() {
        return currentUser!=null
                && currentUser.getRoleName()!=null
                && Role.Roles.ADMIN.name().equals(currentUser.getRoleName());
    }
}
