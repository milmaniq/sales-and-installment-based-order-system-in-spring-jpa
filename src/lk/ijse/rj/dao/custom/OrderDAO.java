/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.dao.CrudDAO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderItem;

/**
 *
 * @author Ilman Iqbal
 */
public interface OrderDAO extends CrudDAO<Order, String> {

    public List<Order> getOrderByOrderId(String id) throws Exception;
    
    public List<Order> getOrderByName(String name) throws Exception;
    
    public List<Order> getOrderByContact(String contact) throws Exception;

    public List<Object[]> getOrderByDateOfOrder(String dateOrder) throws Exception;
    
    public List<Object[]> getOrderByDateOfDelivery(String dateDelivery) throws Exception;
    
    public void updateBalanceByOrderId(Order entity) throws Exception;

    public void updateDateOfActualDeliveryByOrderId(Order order) throws Exception;
    
    
}
