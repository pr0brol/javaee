package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;

public class MainServlet implements Servlet, Serializable {

    private Logger logger = LoggerFactory.getLogger(MainServlet.class);
    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("new request");
        servletResponse.getWriter().println("<h1>hello from MainServlet</h1>");
        servletResponse.getWriter().println("<a href='main'>Main</a>");
        servletResponse.getWriter().println("<a href='catalog'>Catalog</a>");
        servletResponse.getWriter().println("<a href='product'>Product</a>");
        servletResponse.getWriter().println("<a href='cart'>Cart</a>");
        servletResponse.getWriter().println("<a href='order'>Order</a>");
    }

    @Override
    public String getServletInfo() {
        return "HelloWorld from MainServlet";
    }

    @Override
    public void destroy() {
        logger.info("destroyMethod from MainServlet");
    }
}
