/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lk.ijse.rj.dao.CrudDAOImpl;
import lk.ijse.rj.dao.custom.SaleDAO;
import lk.ijse.rj.entity.Employee;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.Sale;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ilman Iqbal
 */

@Component
public class SaleDAOImpl extends CrudDAOImpl<Sale, String> implements SaleDAO {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    private ArrayList<Sale> getArrayList(ResultSet rst) throws Exception{
//        ArrayList<Sale> sales = new ArrayList<>();
//        while (rst.next()){
//            Sale sale = new Sale(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDate(5),
//                    rst.getBigDecimal(6), rst.getBigDecimal(7), rst.getBigDecimal(8),
//                    rst.getDate(9), rst.getString(10), rst.getString(11));
//            sales.add(sale);
//        }
//        return sales;
//    }
    
    @Override
    public List<Sale> getSaleBySaleId(String id) throws Exception {
        return entityManager.createQuery("FROM Sale s WHERE s.saleId LIKE ?1 " +
                "ORDER BY cast(substring(s.saleId,2) as integer) DESC")
                .setParameter(1, id)
                .getResultList();
    }

    @Override
    public List<Sale> getSaleByName(String name) throws Exception {
        return entityManager.createQuery("FROM Sale s WHERE s.name LIKE ?1 " +
                "ORDER BY cast(substring(s.saleId,2) as integer) DESC")
                .setParameter(1, name)
                .getResultList();
    }

    @Override
    public List<Sale> getSaleByContact(String contact) throws Exception {
        return entityManager.createQuery("FROM Sale s WHERE s.contact LIKE ?1 " +
                "ORDER BY cast(substring(s.saleId,2) as integer) DESC")
                .setParameter(1, contact)
                .getResultList();
    }

    @Override
    public List<Object[]> getSaleByDateOfSale(String date) throws Exception {
        return entityManager.createNativeQuery("SELECT saleId, `name`, address, contact, dateOfSale, " +
                "total, buyGold, netTotal, dateOfCancellation, employeeId, orderId " +
                "FROM Sale s " +
                "WHERE s.dateOfSale LIKE ?1 " +
                "ORDER BY convert(substring(s.saleId,2),signed integer) DESC")
                .setParameter(1, date)
                .getResultList();
    }
    
    @Override
    public List<Object[]> getSaleByOrder(String order) throws Exception {
        return entityManager.createNativeQuery("SELECT saleId, `name`, address, contact, dateOfSale, " +
                "total, buyGold, netTotal, dateOfCancellation, employeeId, orderId " +
                "FROM Sale s " +
                "WHERE s.orderId LIKE ?1 " +
                "ORDER BY convert(substring(s.saleId,2),signed integer) DESC")
                .setParameter(1, order)
                .getResultList();
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    

}
