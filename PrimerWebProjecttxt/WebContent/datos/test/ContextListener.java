package org.is.webApp;

import javax.servlet.*;

public final class ContextListener implements ServletContextListener {

    public void contextInitialized (ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext ();
        try {
        
            ListaUsuarios usuariosBD = new ListaUsuarios();
            servletContext.setAttribute ("usuariosLista", usuariosBD);
        }
        catch (Exception e) {
            servletContext.log ("No se pudo crear el atributo de contexto : " + e.getMessage ());
        }
    }

    public void contextDestroyed (ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext ();
        servletContext.removeAttribute ("usuariosLista");
        
    }




}