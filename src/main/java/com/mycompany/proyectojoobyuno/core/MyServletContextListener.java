package com.mycompany.proyectojoobyuno.core;

import com.mycompany.proyectojoobyuno.App;
import javax.servlet.ServletContextEvent;
import org.jooby.servlet.ServerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServletContextListener extends ServerInitializer {
    
    private Logger LOG = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        super.contextDestroyed(sce);
        LOG.debug("Parando el pool de conexion");
        MyPoolConnection.closed();
        LOG.debug("Pool de conexion parado");
    }
    
    
    
}
