/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.business.custom.OrderBalancePaymentBO;
import lk.ijse.rj.dao.custom.OrderBalancePaymentDAO;
import lk.ijse.rj.dao.custom.OrderDAO;
import lk.ijse.rj.dto.OrderBalancePaymentDTO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderBalancePayment;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ilman Iqbal
 */

@Component
@Transactional
public class OrderBalancePaymentBOImpl implements OrderBalancePaymentBO {

    @Autowired
    private OrderBalancePaymentDAO orderBalancePaymentDAO;

    @Autowired
    private OrderDAO orderDAO;


    public OrderBalancePaymentBOImpl() {

    }

    @Override
    public ArrayList<OrderBalancePaymentDTO> getAllOrderBalancePaymentByOrderId(String id) throws Exception {
        try {
            Order order = orderDAO.find(id);
            List<OrderBalancePayment> orderBalancePayments = orderBalancePaymentDAO.getAllOrderBalancePaymentsByOrder(order);

            ArrayList<OrderBalancePaymentDTO> orderBalancePaymentDTOs = new ArrayList<>();
            for (OrderBalancePayment orderBalancePayment : orderBalancePayments) {
                OrderBalancePaymentDTO orderBalancePaymentDTO = new OrderBalancePaymentDTO(orderBalancePayment.getInc(),
                        orderBalancePayment.getOrder().getOrderId(), orderBalancePayment.getDate(), orderBalancePayment.getAmount());
                orderBalancePaymentDTOs.add(orderBalancePaymentDTO);
            }


            return orderBalancePaymentDTOs;
        }catch (HibernateException ex){
            return null;
        }
    }

    @Override
    public boolean insertOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception {

        try {

            Order order = orderDAO.find(dto.getOrderId());

            OrderBalancePayment orderBalancePayment = new OrderBalancePayment(dto.getDateOfPayment(), dto.getAmount(),
                    order);

            orderBalancePaymentDAO.insert(orderBalancePayment);

            Order order1 = orderDAO.find(dto.getOrderId());

            BigDecimal balance = order1.getBalance();
            balance = balance.subtract(dto.getAmount());
            Order order2 = new Order(dto.getOrderId(), balance);

            orderDAO.updateBalanceByOrderId(order2);


            return true;
        }catch (HibernateException ex){
            return false;
        }
    }
    
    @Override
    public boolean deleteOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception {

        try {

            System.out.println(dto);

            orderBalancePaymentDAO.delete(String.valueOf(dto.getInc()));

            Order order = orderDAO.find(dto.getOrderId());
            BigDecimal balance = order.getBalance();
            balance = balance.add(dto.getAmount());

            Order order2 = new Order(dto.getOrderId(), balance);

            orderDAO.updateBalanceByOrderId(order2);


            return true;

        }
        catch(HibernateException e){
            return false;
        }

    }

    ////////////////////checked upto here
    @Override
    public boolean updateOrderBalancePayment(OrderBalancePaymentDTO dto) throws Exception {
        try {
            Order order = orderDAO.find(dto.getOrderId());

            OrderBalancePayment orderBalancePayment = new OrderBalancePayment(dto.getDateOfPayment(), dto.getAmount(), order);
            orderBalancePaymentDAO.update(orderBalancePayment);

            return true;
        }
        catch(HibernateException e){
            return false;
        }
    }

    

    @Override
    public ArrayList<OrderBalancePaymentDTO> getAllOrderBalancePayments() throws Exception {
        try {

            List<OrderBalancePayment> all = orderBalancePaymentDAO.getAll();
            ArrayList<OrderBalancePaymentDTO> allPayments = new ArrayList<>();
            for (OrderBalancePayment a : all) {
                OrderBalancePaymentDTO balancePaymentDTO = new OrderBalancePaymentDTO(a.getOrder().getOrderId(), a.getDate(), a.getAmount());
                allPayments.add(balancePaymentDTO);
            }


            return allPayments;
        }
        catch(HibernateException ex){
            return null;
        }
    }

    @Override
    public OrderBalancePaymentDTO findOrderBalancePayment(String id) throws Exception {
        try {


            OrderBalancePayment bal = orderBalancePaymentDAO.find(id);
            OrderBalancePaymentDTO orderBalancePaymentDTO = new OrderBalancePaymentDTO(bal.getOrder().getOrderId(), bal.getDate(), bal.getAmount());

            return orderBalancePaymentDTO;
        }
        catch(HibernateException ex){
            return null;
        }


    }

}
