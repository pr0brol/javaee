package ru.geekbrains.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repository.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        String jdbcConnectionString = ctx.getInitParameter("jdbcConnectionString");
        String dbUsername = ctx.getInitParameter("dbUsername");
        String dbPassword = ctx.getInitParameter("dbPassword");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, dbUsername, dbPassword);
            ProductRepository productRepository = new ProductRepository(conn);
            ctx.setAttribute("connection", conn);
            ctx.setAttribute("productRepository", productRepository);

            if (productRepository.findAll().size() == 0) {
                productRepository.insert(new Product(-1L, "Milk", "Fresh Milk", new BigDecimal(70)));
                productRepository.insert(new Product(-1L, "Bread", "Fresh Bread", new BigDecimal(40)));
                productRepository.insert(new Product(-1L, "Water", "Fresh Water", new BigDecimal(50)));
                productRepository.insert(new Product(-1L, "Cheese", "Good Cheese", new BigDecimal(200)));
                productRepository.insert(new Product(-1L, "Potato", "Good Potato", new BigDecimal(25)));
                productRepository.insert(new Product(-1L, "Tomato", "Fresh Tomato", new BigDecimal(70)));
                productRepository.insert(new Product(-1L, "Juice", "Cold Juice", new BigDecimal(45)));
                productRepository.insert(new Product(-1L, "Orange", "Fresh Orange", new BigDecimal(80)));
                productRepository.insert(new Product(-1L, "Sweets", "Good Sweets", new BigDecimal(150)));
            }
        } catch (SQLException ex) {
            logger.error("", ex);
        }
    }
}
