package com.kane.sol.components;

import com.kane.sol.pages.Login;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import javax.servlet.http.HttpServletRequest;

public class Header {
    @Property
    private final String companyName = "Kane Solutions";
    @Property
    private final String companyAddress = "123 Corporate Lane, Noida";


    @Inject
    private HttpServletRequest request;

    /**
     * Handles the click on the logout link (t:id="logout").
     * Invalidates the session and navigates to the Login page.
     */
    @OnEvent(value = EventConstants.ACTION, component = "logout")
    Object onLogout() {
        // Invalidate existing session (clears @Persist(SESSION) fields, etc.)
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
        // Redirect user back to the Login page
        return Login.class;
    }
}
