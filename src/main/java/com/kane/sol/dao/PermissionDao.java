package com.kane.sol.dao;

public interface PermissionDao {
    boolean hasPermission(Long employeeId, String permissionName);
}

