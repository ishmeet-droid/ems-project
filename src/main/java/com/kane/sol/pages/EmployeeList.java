package com.kane.sol.pages;

import com.kane.sol.dto.EmployeeDto;
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
import org.apache.tapestry5.http.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.List;

public class EmployeeList {


    private static final Logger logger = LogManager.getLogger(EmployeeList.class);


    @Inject
    private EmployeeService employeeService;

    @Property
    private EmployeeDto employee;

//    @Persist(PersistenceConstants.SESSION)
//    @Property
//    private Employee currentUser;

    @SessionState(create=true)
    @Property
    private UserSession userSession;

    @Property
    private String gridAdd;


    @Property
    private String query;

    @Inject
    private PageRenderLinkSource linkSource;

    void setupRender()
    {
        logger.info("currentUser: {}", userSession.getCurrentUser() != null ? userSession.getCurrentUser().getName() : null);
        gridAdd = userSession.isAdmin() ? "edit,delete" : "";
        logger.info("isAdmin : {}", userSession.isAdmin());
    }

    public List<EmployeeDto> getEmployees() {
        return employeeService.getAllEmployees();
    }

    Object onActionFromDelete(Long id)
    {
        employeeService.deleteEmployee(id);
        return this;
    }


    List<String> onProvideCompletionsFromQuery(String partial) {
        return employeeService.suggestEmployeeNames(partial);
    }

    Object onSuccessFromSearchForm() {

        EmployeeDto e = employeeService.getAllEmployees().stream()
                .filter(emp -> emp.getName().equalsIgnoreCase(query))
                .findFirst()
                .orElse(null);


        if (e != null) {
            Link link = linkSource.createPageRenderLinkWithContext(EmployeeDetails.class, e.getId());
            return link;
            }
            return this;

    }

}