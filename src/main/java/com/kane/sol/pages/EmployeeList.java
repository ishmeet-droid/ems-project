package com.kane.sol.pages;

import com.kane.sol.entities.Employee;
import com.kane.sol.security.UserSession;
import com.kane.sol.services.EmployeeService;
import com.kane.sol.services.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class EmployeeList {


    private static final Logger logger = LogManager.getLogger(EmployeeList.class);


    @Inject
    private EmployeeService employeeService;

    @Property
    private Employee employee;

//    @Persist(PersistenceConstants.SESSION)
//    @Property
//    private Employee currentUser;

    @SessionState(create=true)
    @Property
    private UserSession userSession;

    @Property
    private String gridAdd;

    void setupRender()
    {
        logger.info("currentUser: {}", userSession.getCurrentUser() != null ? userSession.getCurrentUser().getName() : null);
        gridAdd = userSession.isAdmin() ? "edit,delete" : "";
        logger.info("isAdmin : {}", userSession.isAdmin());
    }
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    Object onActionFromDelete(Long id)
    {
        employeeService.deleteEmployee(id);
        return this;
    }
}