/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.List;

import lk.ijse.rj.dao.CrudDAO;
import lk.ijse.rj.entity.Order;
import lk.ijse.rj.entity.Sale;

/**
 *
 * @author Ilman Iqbal
 */
public interface SaleDAO extends CrudDAO<Sale, String> {
    public List getSaleBySaleId(String id) throws Exception;
    
    public List<Sale> getSaleByName(String name) throws Exception;
    
    public List<Sale> getSaleByContact(String contact) throws Exception;
    
    public List<Object[]> getSaleByDateOfSale(String date) throws Exception;
    
    public List<Object[]> getSaleByOrder(String order) throws Exception;
}
