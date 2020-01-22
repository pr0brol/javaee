package ru.geekbrains.jsfControllers;

import ru.geekbrains.entity.Cart;
import ru.geekbrains.entity.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScoped
@Named
public class CartController implements Serializable {

    @Inject
    private Cart cart;

    public HashMap<Product, Integer> getAllProductsInCart(){
        return cart.getProductsInCart();
    }

    public Collection<Integer> getAllCounts() {
        return cart.getProductsInCart().values();
    }

    public Collection<Product> getAllProducts() {
        return cart.getProductsInCart().keySet();
    }

    public void addProductInCart(Product product){
        for (Map.Entry<Product, Integer> entity: getAllProductsInCart().entrySet()) {       //TODO метод вроде рабочий, но всё равно повторно добавляет
            if(entity.getKey().getId() == (product.getId())) {
                entity.setValue(entity.getValue() + 1);
                break;
            }else {
                cart.getProductsInCart().put(product, 1);
                break;
            }
        }
    }

}
