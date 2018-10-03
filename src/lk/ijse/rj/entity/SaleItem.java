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

@NamedNativeQueries({
        @NamedNativeQuery(name = "updateSaleItem", query = "UPDATE SaleItem SET description=:description, price=:price WHERE itemId=:itemId")
})

@Entity
public class SaleItem {

    @Id
    @GeneratedValue
    int id;
    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private
    Stock stock;
    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "saleId", referencedColumnName = "saleId")
    private
    Sale sale;

    public SaleItem() {
    }

    public SaleItem(Stock stock, String description, BigDecimal price, Sale sale) {
        this.setStock(stock);
        this.setDescription(description);
        this.setPrice(price);
        this.setSale(sale);
    }

    public SaleItem(int id, Stock stock, String description, BigDecimal price, Sale sale) {
        this.id = id;
        this.setStock(stock);
        this.setDescription(description);
        this.setPrice(price);
        this.setSale(sale);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

//    @Override
//    public String toString() {
//        return "SaleItem{" +
//                "id=" + id +
//                ", stock=" + stock +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", sale=" + sale +
//                '}';
//    }
}
