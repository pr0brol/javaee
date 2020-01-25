package ru.geekbrains.services;

import ru.geekbrains.Cart.LineItem;
import ru.geekbrains.entity.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
public class CartService implements Serializable {

    private Map<LineItem, Integer> lineItems;

    public CartService() {
        lineItems = new HashMap<>();
    }

    public void addProductQty(Product product, String color, int qty) {
        LineItem lineItem = new LineItem(product, color);
        lineItem.setPrice(product.getPrice());
        lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0) + qty);
        lineItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(lineItems.get(product))));
    }

    public void removeProductQty(Product product, String color, int qty) {
        LineItem lineItem = new LineItem(product, color);
        lineItem.setPrice(product.getPrice());
        int currentQty = lineItems.getOrDefault(lineItem, 0);
        if(currentQty - qty > 0) {
            lineItems.put(lineItem, currentQty - qty);
        } else {
            lineItems.remove(lineItem);
        }
        lineItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(lineItems.get(product))));
    }

    public List<LineItem> getLineItems() {
        lineItems.forEach(LineItem::setQuantity);
        return new ArrayList<>(lineItems.keySet());
    }
}
