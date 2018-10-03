/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ijse.rj.dao.CrudDAOImpl;
import lk.ijse.rj.dao.custom.OrderDAO;
import lk.ijse.rj.entity.Order;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

/**
 * @author Ilman Iqbal
 */

@Component
public class OrderDAOImpl extends CrudDAOImpl<Order, String> implements OrderDAO {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public List<Order> getOrderByOrderId(String id) throws Exception {
        return entityManager.createQuery("FROM Order o WHERE o.orderId LIKE ?1 " +
                "ORDER BY cast(substring(o.orderId,2) as integer) DESC")
                .setParameter(1, id)
                .getResultList();
    }

    @Override
    public List<Order> getOrderByName(String name) throws Exception {
        return entityManager.createQuery("FROM Order o WHERE o.name LIKE ?1 " +
                "ORDER BY cast(substring(o.orderId,2) as integer) DESC")
                .setParameter(1, name)
                .getResultList();
    }

    @Override
    public List<Order> getOrderByContact(String contact) throws Exception {
        return entityManager.createQuery("FROM Order o WHERE o.contact LIKE ?1 " +
                "ORDER BY cast(substring(o.orderId,2) as integer) DESC")
                .setParameter(1, contact)
                .getResultList();
    }

    @Override
    public List<Object[]> getOrderByDateOfOrder(String dateOrder) throws Exception {
        return entityManager.createNativeQuery("SELECT orderId, `name`, address, contact, dateOfOrder," +
                "dateOfDelivery, total, buyGold, advance, netTotal, balance," +
                "dateOfActualDelivery, dateOfCancellation, employeeId " +
                "FROM `Order` o WHERE o.dateOfOrder LIKE ?1 " +
                "ORDER BY convert(substring(o.orderId,2),signed integer) DESC")
                .setParameter(1, dateOrder)
                .getResultList();
    }

    @Override
    public List<Object[]> getOrderByDateOfDelivery(String dateDelivery) throws Exception {
        return entityManager.createNativeQuery("SELECT orderId, `name`, address, contact, dateOfOrder," +
                "dateOfDelivery, total, buyGold, advance, netTotal, balance," +
                "dateOfActualDelivery, dateOfCancellation, employeeId " +
                "FROM `Order` o WHERE o.dateOfDelivery LIKE ?1 " +
                "ORDER BY convert(substring(o.orderId,2),signed integer) DESC")
                .setParameter(1, dateDelivery)
                .getResultList();
    }

    @Override
    public void updateBalanceByOrderId(Order entity) throws Exception {
        entityManager.createNativeQuery("UPDATE `Order` SET balance = ?1 WHERE orderId = ?2")
                .setParameter(1, entity.getBalance())
                .setParameter(2, entity.getOrderId())
                .executeUpdate();
    }


    @Override
    public void updateDateOfActualDeliveryByOrderId(Order order) throws Exception {
        entityManager.createNativeQuery("UPDATE `Order` SET dateOfActualDelivery=?1 WHERE orderId=?2")
                .setParameter(1, order.getDateOfActualDelivery())
                .setParameter(2, order.getOrderId())
                .executeUpdate();
    }

    ////////checked upto here


}
