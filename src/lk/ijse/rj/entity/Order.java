/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ilman Iqbal
 */

@Entity
@Table(name = "`Order`")
public class Order {
    @Id
    private String orderId;
    private String name;
    private String address;
    private String contact;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfOrder;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfDelivery;
    private BigDecimal total;
    private BigDecimal buyGold;
    private BigDecimal advance;
    private BigDecimal netTotal;
    private BigDecimal balance;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfActualDelivery;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfCancellation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private
    Employee employee;

    @OneToOne(mappedBy = "order")
    private
    Sale sale;

    @OneToMany(mappedBy = "order")
    private
    List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private
    List<OrderBalancePayment> orderBalancePayments = new ArrayList<>();


    public Order() {
    }

    public Order(String orderId, BigDecimal balance){
        this.setOrderId(orderId);
        this.setBalance(balance);
    }
    
    public Order(String orderId, Date dateOfActualDelivery){
        this.setOrderId(orderId);
        this.setDateOfActualDelivery(dateOfActualDelivery);
    }

    public Order(String orderId, String name, String address, String contact, Date dateOfOrder, Date dateOfDelivery, BigDecimal total, BigDecimal buyGold, BigDecimal advance, BigDecimal netTotal, BigDecimal balance, Date dateOfActualDelivery, Date dateOfCancellation, Employee employee) {
        this.setOrderId(orderId);
        this.setName(name);
        this.setAddress(address);
        this.setContact(contact);
        this.setDateOfOrder(dateOfOrder);
        this.setDateOfDelivery(dateOfDelivery);
        this.setTotal(total);
        this.setBuyGold(buyGold);
        this.setAdvance(advance);
        this.setNetTotal(netTotal);
        this.setBalance(balance);
        this.setDateOfActualDelivery(dateOfActualDelivery);
        this.setDateOfCancellation(dateOfCancellation);
        this.setEmployee(employee);
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getBuyGold() {
        return buyGold;
    }

    public void setBuyGold(BigDecimal buyGold) {
        this.buyGold = buyGold;
    }

    public BigDecimal getAdvance() {
        return advance;
    }

    public void setAdvance(BigDecimal advance) {
        this.advance = advance;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getDateOfActualDelivery() {
        return dateOfActualDelivery;
    }

    public void setDateOfActualDelivery(Date dateOfActualDelivery) {
        this.dateOfActualDelivery = dateOfActualDelivery;
    }

    public Date getDateOfCancellation() {
        return dateOfCancellation;
    }

    public void setDateOfCancellation(Date dateOfCancellation) {
        this.dateOfCancellation = dateOfCancellation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderBalancePayment> getOrderBalancePayments() {
        return orderBalancePayments;
    }

    public void setOrderBalancePayments(List<OrderBalancePayment> orderBalancePayments) {
        this.orderBalancePayments = orderBalancePayments;
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "orderId='" + orderId + '\'' +
//                ", name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", contact='" + contact + '\'' +
//                ", dateOfOrder=" + dateOfOrder +
//                ", dateOfDelivery=" + dateOfDelivery +
//                ", total=" + total +
//                ", buyGold=" + buyGold +
//                ", advance=" + advance +
//                ", netTotal=" + netTotal +
//                ", balance=" + balance +
//                ", dateOfActualDelivery=" + dateOfActualDelivery +
//                ", dateOfCancellation=" + dateOfCancellation +
//                ", employee=" + employee +
//                ", sale=" + sale +
//                ", orderItems=" + orderItems +
//                ", orderBalancePayments=" + orderBalancePayments +
//                '}';
//    }
}
