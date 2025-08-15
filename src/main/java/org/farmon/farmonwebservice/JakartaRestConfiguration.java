package org.farmon.farmonwebservice;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new HashSet<>();
//        resources.add(ExampleService.class);
//        return resources;
//    }
}
