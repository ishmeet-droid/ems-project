package com.kane.sol.pages;


import com.kane.sol.entities.Employee;
import com.kane.sol.security.UserSession;
import com.kane.sol.services.EmployeeService;
import com.kane.sol.services.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Login {

    private static final Logger logger = LogManager.getLogger(Login.class);

    @Property
    private String username;

    @Property
    private String password;

//    @Inject
//    private EmployeeService employeeService;

    @Inject
    private LoginService loginService;

    @InjectPage
    private EmployeeList employeeList;

    @Property
    private String errorMessage;

//    @Persist(PersistenceConstants.SESSION)
//    @Property
//    private Employee currentUser;

    @SessionState(create=true)
    @Property
    private UserSession userSession;

    Object onSuccess() {
        Employee e = loginService.validate(username, password);
        if (e != null) {
            userSession.setCurrentUser(e);
            logger.info("userExists");
            return employeeList;
        }
        errorMessage = "Invalid credentials!";
        return null; // Stay on same page
    }
}