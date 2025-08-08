package com.kane.sol.services;

import com.kane.sol.entities.Employee;
import com.kane.sol.pages.Login;
import com.kane.sol.services.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;


public class LoginServiceImpl implements LoginService {


    private static final Logger logger = LogManager.getLogger(LoginService.class);

    @Inject
    private EmployeeService employeeService;

//    @Persist(PersistenceConstants.SESSION)
//    @Property
//    private Employee currentUser;

    @Log
    @Override
    public Employee validate(String username, String password) {
        Employee emp = employeeService.findByCredentials(username, password);
        if (emp != null) {
//            this.currentUser = e;
            logger.info("userExists");
            return emp;
        }
        return null;
    }
}