package ru.geekbrains.entity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

@SessionScoped
@Named
public class Cart implements Serializable {

    private HashMap<Product, Integer> productsInCart = new HashMap<>();


    @PostConstruct
    private void init(){
        productsInCart.put(new Product(10L, "test1", "descTest", new BigDecimal(50.0)), 1); // TODO без этой строки не добавляется

    }

    public HashMap<Product, Integer> getProductsInCart() {
        return productsInCart;
    }

}
