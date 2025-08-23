package com.kane.sol.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.commons.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

public class AppModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(LoginService.class, LoginServiceImpl.class);
        binder.bind(EmployeeService.class, EmployeeServiceImpl.class);
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
        configuration.add("tapestry.production-mode", "false");
        configuration.add("tapestry.hmac-passphrase", "Secret-KEey");
        configuration.add(SymbolConstants.START_PAGE_NAME, "login");
    }
}
