package com.kane.sol.components;

import com.kane.sol.pages.EmployeeDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ComponentResources;

import java.time.LocalDate;

public class EmpBday {



    private static final Logger logger = LogManager.getLogger(EmployeeDetails.class);

    @Parameter(required=true) private Long employeeId;
    @Parameter(required=true) private String employeeName;
    @Parameter(required=true) private String gender;    // "M" or "F"
    @Parameter(required=true) private LocalDate birthDate;

    @Inject private ComponentResources resources;

    public boolean getShowToday() {
        LocalDate today = LocalDate.now();
        return birthDate != null && today.getMonth() == birthDate.getMonth() && today.getDayOfMonth() == birthDate.getDayOfMonth();
    }

    public String getBannerText() { return "Happy Birthday, " + employeeName + "!"; }
    public String getBannerColor() { return "F".equalsIgnoreCase(gender) ? "pink" : "lightblue"; }

    void setupRender() {
        if (getShowToday()) {
            logger.info("Triggering On Wished event");
            resources.triggerEvent("wished", new Object[]{employeeId}, null); // bubbles up
        }
    }
}
