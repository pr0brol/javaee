package ru.geekbrains.repository;

import ru.geekbrains.entity.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductRepositoryWs {

    @WebMethod
    List<Product> findAll();

    @WebMethod
    void insert(Product product);

    @WebMethod
    void update(Product product);

    @WebMethod
    void delete(long id);

    @WebMethod
    Product findById(long id);
}
