/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ilman Iqbal
 */

@Entity
public class Stock {
    @Id
    private String itemId;
    private String description;
    private String weight;
    private BigDecimal rate;
    @Temporal(value = TemporalType.DATE)
    private Date dateAdded;

    @OneToOne(mappedBy = "stock")
    private
    OrderItem orderItem;

    @OneToOne(mappedBy = "stock")
    private
    SaleItem saleItem;


    public Stock() {
    }

    public Stock(String itemId, String description, String weight, BigDecimal rate, Date dateAdded) {
        this.setItemId(itemId);
        this.setDescription(description);
        this.setWeight(weight);
        this.setRate(rate);
        this.setDateAdded(dateAdded);
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public SaleItem getSaleItem() {
        return saleItem;
    }

    public void setSaleItem(SaleItem saleItem) {
        this.saleItem = saleItem;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", weight='" + weight + '\'' +
                ", rate=" + rate +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
