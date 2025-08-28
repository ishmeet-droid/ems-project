package com.kane.sol.pages;

import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;
import com.kane.sol.entities.Role;
import com.kane.sol.services.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.ActivationRequestParameter;

public class EmployeeDetails {


    private static final Logger logger = LogManager.getLogger(EmployeeDetails.class);


    @Inject
    private EmployeeService employeeService;

    @Property
    private EmployeeDto employee;



    @Persist(PersistenceConstants.FLASH)
    @Property
    private String infoMessage;


    @Persist(PersistenceConstants.FLASH)
    @Property
    private String errorMessage;

    @Inject
    @Path("context:images/apache-tapestry.png")
    @Property
    private Asset empImage;

    void onActivate(Long id) {
        employee = employeeService.getEmployeeById(id);
    }

    Long onPassivate() {
        return employee != null ? employee.getId() : null;
    }

    Object onPromote(Long id) {

        employee = employeeService.getEmployeeById(id);
        if(Role.Roles.ADMIN.name().equals(employee.getRoleName())){
            errorMessage = "Can't Promote Admin...";
            return null;
        }
        employeeService.promoteToManager(id);
        employee = employeeService.getEmployeeById(id);
        infoMessage = "Promoted to " + employee.getRoleName();

        logger.info(infoMessage);
        return null;
    }

    //FMK: Listen to bubbling event from child component (WishBanner -> EmpBday -> Page)
    Object onWished(Long id) {
        infoMessage = "Birthday wish delivered for employee #" + id;

        logger.info(infoMessage);

        return null;
    }

}
