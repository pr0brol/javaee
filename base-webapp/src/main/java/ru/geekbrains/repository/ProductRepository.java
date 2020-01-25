package ru.geekbrains.repository;

import ru.geekbrains.entity.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.List;

@ApplicationScoped
@Named
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @PostConstruct
    public void init() throws SQLException {
    }

    @Transactional
    public void insert(Product product) {
        entityManager.persist(product);
    }

    @Transactional
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Transactional
    public void delete(long id) {
        Product product = entityManager.find(Product.class, id);
        if(product != null) {
            entityManager.remove(product);
        }
    }

    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

}
