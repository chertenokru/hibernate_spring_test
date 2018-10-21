package ru.chertenok.spring.hibernate;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class WebStart {
    //    public static Logger logger =  Logger.getLogger(Main.class);
    public static final int PORT = 8081;

    public static void main(String[] args) {

        Server server = new Server(PORT);

        ProtectionDomain domain = WebStart.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());


        server.setHandler(webapp);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
