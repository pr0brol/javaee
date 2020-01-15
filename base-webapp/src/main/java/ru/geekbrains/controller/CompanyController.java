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

@WebServlet(name = "CompanyController", urlPatterns = "/about")
public class CompanyController extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(CompanyController.class);

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
        if (req.getServletPath().equals("/about")) {
            getServletContext().getRequestDispatcher("/company.jsp").forward(req, resp);
        }
    }
}
