/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.dao.CrudDAOImpl;
import lk.ijse.rj.dao.custom.SaleItemDAO;
import lk.ijse.rj.entity.Sale;
import lk.ijse.rj.entity.SaleItem;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.security.sasl.SaslException;

/**
 *
 * @author Ilman Iqbal
 */

@Component
public class SaleItemDAOImpl extends CrudDAOImpl<SaleItem, String> implements SaleItemDAO{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public List<SaleItem> getSaleItemBySale(Sale sale) throws Exception {
        
        return entityManager.createQuery("FROM SaleItem si WHERE si.sale=?1")
                .setParameter(1, sale)
                 .getResultList();
        

    }
    
    //////////////////checked upto here


    
    
}
