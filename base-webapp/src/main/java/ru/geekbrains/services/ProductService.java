package ru.geekbrains.services;

import ru.geekbrains.entity.Product;
import ru.geekbrains.entity.ProductDao;
import ru.geekbrains.repository.ProductRepository;

import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductService {

    @EJB
    private ProductRepository productRepository;

    public void insert(ProductDao productDao) {
        productRepository.insert(productDaoToProduct(productDao));
    }

    public void update(ProductDao productDao) {
        productRepository.update(productDaoToProduct(productDao));
    }

    public void delete(long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            productRepository.delete(id);
        }
    }

    public ProductDao findById(long id) {
        Product product = productRepository.findById(id);
        return productToProductDao(product);
    }

    public List<ProductDao> findAll() {
        List<ProductDao> productDaos = new ArrayList<>();
        for (Product product: productRepository.findAll()) {
            productDaos.add(productToProductDao(product));
        }
        return productDaos;
    }

    public Product productDaoToProduct(ProductDao productDao) {
        Product product = new Product();
        product.setId(productDao.getId());
        product.setName(productDao.getName());
        product.setDescription(productDao.getDescription());
        product.setPrice(productDao.getPrice());
        return product;
    }

    public ProductDao productToProductDao(Product product) {
        ProductDao productDao = new ProductDao();
        productDao.setId(product.getId());
        productDao.setName(product.getName());
        productDao.setDescription(product.getDescription());
        productDao.setPrice(product.getPrice());
        return productDao;
    }
}
