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
public class OrderBalancePayment {
    @Id
    @GeneratedValue
    private int inc;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private
    Order order;

    public OrderBalancePayment() {
    }

    public OrderBalancePayment(int inc, Date date, BigDecimal amount, Order order) {
        this.inc = inc;
        this.setDate(date);
        this.setAmount(amount);
        this.setOrder(order);
    }

    public OrderBalancePayment(Date date, BigDecimal amount, Order order) {
        this.setDate(date);
        this.setAmount(amount);
        this.setOrder(order);
    }


    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderBalancePayment{" +
                "inc=" + inc +
                ", date=" + date +
                ", amount=" + amount +
                ", order=" + order +
                '}';
    }
}
