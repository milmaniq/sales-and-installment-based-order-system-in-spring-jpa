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
public class Sale {
    @Id
    private String saleId;
    private String name;
    private String address;
    private String contact;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfSale;
    private BigDecimal total;
    private BigDecimal buyGold;
    private BigDecimal netTotal;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfCancellation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId",referencedColumnName = "employeeId")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private
    Order order;

    @OneToMany(mappedBy = "sale")
    private
    List<SaleItem> saleItems = new ArrayList<>();

    public Sale() {
    }

    public Sale(String saleId, String name, String address, String contact, Date dateOfSale, BigDecimal total, BigDecimal buyGold, BigDecimal netTotal, Date dateOfCancellation, Employee employee, Order order) {
        this.setSaleId(saleId);
        this.setName(name);
        this.setAddress(address);
        this.setContact(contact);
        this.setDateOfSale(dateOfSale);
        this.setTotal(total);
        this.setBuyGold(buyGold);
        this.setNetTotal(netTotal);
        this.setDateOfCancellation(dateOfCancellation);
        this.setEmployee(employee);
        this.setOrder(order);
    }




    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
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

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
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

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
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


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }


//    @Override
//    public String toString() {
//        return "Sale{" +
//                "saleId='" + saleId + '\'' +
//                ", name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", contact='" + contact + '\'' +
//                ", dateOfSale=" + dateOfSale +
//                ", total=" + total +
//                ", buyGold=" + buyGold +
//                ", netTotal=" + netTotal +
//                ", dateOfCancellation=" + dateOfCancellation +
//                ", employee=" + employee +
//                ", order=" + order +
//                ", saleItems=" + saleItems +
//                '}';
//    }
}
