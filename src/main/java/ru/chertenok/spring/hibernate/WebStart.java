package ru.chertenok.spring.hibernate;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.EnumSet;

public class WebStart {
    //    public static Logger logger =  Logger.getLogger(Main.class);
    public static final int PORT = 8080;

    public static void main(String[] args) {

        Server server = new Server(PORT);

        ProtectionDomain domain = WebStart.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());
        webapp.addFilter(new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain")), "/*", EnumSet.allOf(DispatcherType.class));
        server.setHandler(webapp);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
