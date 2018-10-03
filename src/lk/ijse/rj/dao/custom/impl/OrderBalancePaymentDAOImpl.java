/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.dao.CrudDAOImpl;
import lk.ijse.rj.dao.custom.OrderBalancePaymentDAO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.OrderBalancePayment;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ilman Iqbal
 */

@Component
public class OrderBalancePaymentDAOImpl extends CrudDAOImpl<OrderBalancePayment, String> implements OrderBalancePaymentDAO {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    
    @Override
    public List<OrderBalancePayment> getAllOrderBalancePaymentsByOrder(Order order) throws Exception {
        List<OrderBalancePayment> orderBalancePaymentList = entityManager.createQuery("FROM OrderBalancePayment obp WHERE obp.order=?1 ORDER BY inc")
                .setParameter(1, order)
                .getResultList();

        return orderBalancePaymentList;
    }

    ///////////////checked upto here
    
}
