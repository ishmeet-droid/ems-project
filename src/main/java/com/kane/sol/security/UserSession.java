package com.kane.sol.security;

import com.kane.sol.entities.Employee;
import com.kane.sol.entities.Role;

public class UserSession {
    private Employee currentUser;
    public Employee getCurrentUser() { return currentUser; }
    public void setCurrentUser(Employee e) { this.currentUser = e; }
    public boolean isAdmin() {
        return currentUser!=null
                && currentUser.getRole()!=null
                && Role.Roles.ADMIN.equals(currentUser.getRole().getName());
    }
}
