/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author Ilman Iqbal
 */

@NamedNativeQuery(name = "upadteOrderItem", query = "UPDATE OrderItem SET description=:description, " +
        "price=:price WHERE itemId=:itemId")

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private
    int id;
    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private
    Stock stock;
    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private
    Order order;

    public OrderItem() {
    }

    public OrderItem(int id, Stock stock, String description, BigDecimal price, Order order) {
        this.setId(id);
        this.setStock(stock);
        this.setDescription(description);
        this.setPrice(price);
        this.setOrder(order);
    }

    public OrderItem(Stock stock, String description, BigDecimal price, Order order) {
        this.setStock(stock);
        this.setDescription(description);
        this.setPrice(price);
        this.setOrder(order);
    }



    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", order=" + order +
                '}';
    }
}
