package presentation.listeners;

import logic.*;
import persistence.DataSource;
import persistence.ProductException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/*
author: madsbrandt
*/

@WebListener()
public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ApplicationListener () {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized (ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        List<Cupcake> cupcakes = null;
        List<IProduct> toppings = null;
        List<IProduct> bottoms = null;
        try {
            InputStream inputStream = ApplicationListener.class.getResourceAsStream("/db.properties");
            if(inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                DataSource.setProperties(properties);
            }
            LogicFacade logicFacade = new LogicFacade();
            cupcakes = logicFacade.getPremadeCupcakes();
            toppings = logicFacade.getAllToppings();
            bottoms = logicFacade.getAllBottoms();
        } catch (ProductException | IOException e) {
            e.printStackTrace();
        }
        sce.getServletContext().setAttribute("cupcakes", cupcakes);
        sce.getServletContext().setAttribute("toppings", toppings);
        sce.getServletContext().setAttribute("bottoms", bottoms);
    }

    public void contextDestroyed (ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated (HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed (HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded (HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved (HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced (HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
