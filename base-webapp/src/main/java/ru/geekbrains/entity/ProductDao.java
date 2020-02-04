package ru.geekbrains.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDao {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    public ProductDao() {
    }

    public ProductDao(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDao productDao = (ProductDao) o;
        return Objects.equals(id, productDao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
