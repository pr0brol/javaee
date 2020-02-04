package ru.geekbrains.jsfControllers;

import ru.geekbrains.Cart.LineItem;
import ru.geekbrains.entity.ProductDao;
import ru.geekbrains.services.CartService;
import ru.geekbrains.services.Logger;
import ru.geekbrains.services.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @Inject
    private CartService cartService;

    private ProductDao productDao;

    private List<ProductDao> products;

    public void preloadProducts(ComponentSystemEvent componentSystemEvent) {
        this.products = productService.findAll();
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Interceptors({Logger.class})
    public String createProduct(){
        this.productDao = new ProductDao();
        return "/product.xhtml?faces-redirect=true";
    }

    @Interceptors({Logger.class})
    public List<ProductDao> getAllProducts() throws SQLException {
        return products;
    }

    @Interceptors({Logger.class})
    public String editProduct(ProductDao productDao) {
        this.productDao = productDao;
        return "/product.xhtml?faces-redirect=true";
    }

    @Interceptors({Logger.class})
    public void deleteProduct(ProductDao productDao) throws SQLException {
        productService.delete(productDao.getId());
    }

    @Interceptors({Logger.class})
    public String saveProduct() throws SQLException {
        if(productDao.getId() == null) {
            productService.insert(productDao);
        } else {
            productService.update(productDao);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    @Interceptors({Logger.class})
    public void addToCart(ProductDao productDao) {
        cartService.addProductQty(productDao, "green", 1);
    }

    public List<LineItem> getLineItems(){
        return cartService.getLineItems();
    }
}
