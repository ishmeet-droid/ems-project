package com.kane.sol.dao;

import com.kane.sol.entities.Role;

public interface RoleDao {

    Role findByName(Role.Roles name);
}
