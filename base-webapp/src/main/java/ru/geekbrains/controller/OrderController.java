package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderController", urlPatterns = "/order")
public class OrderController extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");

        if (productRepository == null) {
            throw new ServletException("ProductRepository not created");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getServletPath().equals("/order")) {
                req.setAttribute("products", productRepository.findAll());
                getServletContext().getRequestDispatcher("/order.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            logger.error("", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
