/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.dao.custom.*;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.OrderItemDTO;
import lk.ijse.rj.entity.Employee;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderItem;
import lk.ijse.rj.entity.Stock;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author Ilman Iqbal
 */

@Component
@Transactional
public class OrderBOImpl implements OrderBO {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private StockDAO stockDAO;


    public OrderBOImpl() {
    }

    private ArrayList<OrderDTO> getArrayList(List<Order> allOrders) throws Exception {
        ArrayList<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order allOrder : allOrders) {
            OrderDTO orderDTO = new OrderDTO(allOrder.getOrderId(), allOrder.getEmployee().getEmployeeId(), allOrder.getName(),
                    allOrder.getAddress(), allOrder.getContact(), allOrder.getDateOfOrder(),
                    allOrder.getDateOfDelivery(), allOrder.getTotal(),
                    allOrder.getBuyGold(), allOrder.getAdvance(), allOrder.getNetTotal(), allOrder.getBalance(),
                    allOrder.getDateOfActualDelivery(), allOrder.getDateOfCancellation());

            orderDTOs.add(orderDTO);

        }
        return orderDTOs;
    }


    @Override
    public ArrayList<OrderDTO> getAllOrders() throws Exception {

        try {

            List<Order> allOrders = orderDAO.getAll();

            return getArrayList(allOrders);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public boolean insertOrder(OrderDTO dto) throws Exception {
        try {

            Employee employee = employeeDAO.find(dto.getEmployeeId());

            Order order = new Order(dto.getOrderId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfOrder(),
                    dto.getDateOfDelivery(), dto.getTotal(), dto.getBuyGold(), dto.getAdvance(), dto.getNetTotal(),
                    dto.getBalance(), dto.getDateOfActualDelivery(), dto.getDateOfCancellation(), employee);
            orderDAO.insert(order);

            ArrayList<OrderItemDTO> orderItemDTOs = dto.getOrderItems();
            for (OrderItemDTO orderItemDTO : orderItemDTOs) {
                Stock stock = stockDAO.find(orderItemDTO.getItemId());
                OrderItem orderItem = new OrderItem(stock, orderItemDTO.getDescription(),
                        orderItemDTO.getPrice(), order);

                orderItemDAO.insert(orderItem);
            }

            return true;

        } catch (HibernateException ex) {
            return false;
        }

    }


    @Override
    public ArrayList<OrderDTO> getOrderByOrderId(String id) throws Exception {
        try {

            List<Order> orders = orderDAO.getOrderByOrderId(id);

            return getArrayList(orders);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<OrderDTO> getOrderByName(String id) throws Exception {
        try {

            List<Order> orders = orderDAO.getOrderByName(id);

            return getArrayList(orders);
        } catch (HibernateException ex) {
            return null;
        }

    }

    @Override
    public ArrayList<OrderDTO> getOrderByContact(String id) throws Exception {
        try {

            List<Order> orders = orderDAO.getOrderByContact(id);

            return getArrayList(orders);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<OrderDTO> getOrderByDateOfOrder(String dateOrder) throws Exception {
        try {

            List<Object[]> orderByDateOfOrder = orderDAO.getOrderByDateOfOrder(dateOrder);
            List<Order> orders = new ArrayList<>();

            for (Object[] objects : orderByDateOfOrder) {
                Employee employee = employeeDAO.find((String) objects[13]);

                Order order = new Order(
                        (String) objects[0],
                        (String) objects[1],
                        (String) objects[2],
                        (String) objects[3],
                        (Date) objects[4],
                        (Date) objects[5],
                        (BigDecimal) objects[6],
                        (BigDecimal) objects[7],
                        (BigDecimal) objects[8],
                        (BigDecimal) objects[9],
                        (BigDecimal) objects[10],
                        (Date) objects[11],
                        (Date) objects[12],
                        employee);
                orders.add(order);
            }

            return getArrayList(orders);
        } catch (HibernateException ex) {
            return null;
        }

    }

    @Override
    public ArrayList<OrderDTO> getOrderByDateOfDelivery(String dateDelivery) throws Exception {
        try {

            List<Object[]> orderByDateOfDelivery = orderDAO.getOrderByDateOfDelivery(dateDelivery);
            List<Order> orders = new ArrayList<>();

            for (Object[] objects : orderByDateOfDelivery) {
                Employee employee = employeeDAO.find((String) objects[13]);

                Order order = new Order(
                        (String) objects[0],
                        (String) objects[1],
                        (String) objects[2],
                        (String) objects[3],
                        (Date) objects[4],
                        (Date) objects[5],
                        (BigDecimal) objects[6],
                        (BigDecimal) objects[7],
                        (BigDecimal) objects[8],
                        (BigDecimal) objects[9],
                        (BigDecimal) objects[10],
                        (Date) objects[11],
                        (Date) objects[12],
                        employee);
                orders.add(order);
            }

            return getArrayList(orders);
        } catch (HibernateException ex) {
            return null;
        }

    }


    @Override
    public OrderDTO findOrder(String id) throws Exception {
        try {

            Order order = orderDAO.find(id);
            OrderDTO orderDTO = new OrderDTO(order.getOrderId(),
                    order.getEmployee().getEmployeeId(),
                    order.getName(), order.getAddress(),
                    order.getContact(), order.getDateOfOrder(), order.getDateOfDelivery(), order.getTotal(),
                    order.getBuyGold(), order.getAdvance(), order.getNetTotal(), order.getBalance(),
                    order.getDateOfActualDelivery(), order.getDateOfCancellation());

            return orderDTO;
        } catch (HibernateException ex) {
            return null;
        }

    }


    @Override
    public ArrayList<OrderItemDTO> getOrderItemsByOrderId(String id) throws Exception {
        try {

            Order order = orderDAO.find(id);
            List<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrder(order);
            ArrayList<OrderItemDTO> orderItemDTOs = new ArrayList<>();
            for (OrderItem orderItem : orderItems) {
                OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem.getStock().getItemId(), orderItem.getDescription(),
                        orderItem.getPrice());
                orderItemDTOs.add(orderItemDTO);
            }

            return orderItemDTOs;
        } catch (HibernateException ex) {
            return null;
        }

    }


    @Override
    public boolean updateOrder(OrderDTO dto) throws Exception {


        try {

            Employee employee = employeeDAO.find(dto.getEmployeeId());

            Order order = new Order(dto.getOrderId(), dto.getName(), dto.getAddress(), dto.getContact(),
                    dto.getDateOfOrder(), dto.getDateOfDelivery(), dto.getTotal(), dto.getBuyGold(), dto.getAdvance(),
                    dto.getNetTotal(), dto.getBalance(), dto.getDateOfActualDelivery(), dto.getDateOfCancellation(),
                    employee);

            orderDAO.update(order);

            List<OrderItemDTO> orderItemDTOs = dto.getOrderItems();

//            for (OrderItemDTO orderItemDTO : orderItemDTOs) {
//
//               entityManager.createNamedQuery("upadteOrderItem")
//                       .setParameter("description", orderItemDTO.getDescription())
//                       .setParameter("price", orderItemDTO.getPrice())
//                       .setParameter("itemId", orderItemDTO.getItemId())
//                       .executeUpdate();
//            }

            return true;

        } catch (HibernateException ex) {
            return false;
        }


    }

    /////////////// checked upto here
}
