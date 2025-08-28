package com.kane.sol.pages;


import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;
import com.kane.sol.security.UserSession;
import com.kane.sol.services.EmployeeService;
import com.kane.sol.services.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Login {

    private static final Logger logger = LogManager.getLogger(Login.class);

    @Property
    private String username;

    @Property
    private String password;

    @Inject
    private EmployeeService employeeService;

    @Inject
    private LoginService loginService;

    @InjectPage
    private EmployeeList employeeList;


    @Persist(PersistenceConstants.FLASH)
    @Property
    private String errorMessage;

//    @Persist(PersistenceConstants.SESSION)
//    @Property
//    private Employee currentUser;

    @SessionState(create=true)
    @Property
    private UserSession userSession;

    @InjectComponent
    private Form loginForm;

//    void onPrepare() {
//        this.errorMessage = null;   // ← clears it before the page renders
//    }
    void onValidateFromLoginForm() {

        EmployeeDto empDto = employeeService.getEmployeeByUserName(username);
        if (username == null || empDto == null) {
            logger.info("Username does not exist.");
            this.errorMessage = "Username does not exist. Talk to admin!!";
            loginForm.recordError("Username does not exist. Talk to admin!!!");
        }
    }


    Object onFailureFromLoginForm() { // validation failed
        errorMessage = "Login failed. Please check your credentials.";

        logger.info(errorMessage);
        return null;
    }

    Object onSuccessFromLoginForm() {
        if (errorMessage != null) return null;
        EmployeeDto e = loginService.validate(username, password);
        if (e == null) {
            loginForm.recordError("Invalid credentials!");
            errorMessage = "Invalid credentials!";
            logger.info(errorMessage);
            return null;
        }
        userSession.setCurrentUser(e); // or setCurrentUserDto(e)
        return EmployeeList.class;
    }
}