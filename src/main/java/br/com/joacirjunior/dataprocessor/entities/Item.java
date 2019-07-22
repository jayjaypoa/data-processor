package br.com.joacirjunior.dataprocessor.entities;

import java.math.BigDecimal;

public class Item extends DefaultLayout {

    private Integer quantity;
    private BigDecimal price;

    public Item(String id, Integer quantity, BigDecimal price) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", id='" + id + '\'' +
                '}';
    }

}
