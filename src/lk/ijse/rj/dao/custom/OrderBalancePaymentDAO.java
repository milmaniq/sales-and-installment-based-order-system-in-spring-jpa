/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.dao.CrudDAO;
import lk.ijse.rj.dto.OrderBalancePaymentDTO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderBalancePayment;

/**
 *
 * @author Ilman Iqbal
 */
public interface OrderBalancePaymentDAO extends CrudDAO<OrderBalancePayment, String> {
    public List<OrderBalancePayment> getAllOrderBalancePaymentsByOrder(Order order) throws Exception;
}
