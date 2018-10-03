/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.dao.CrudDAO;
import lk.ijse.rj.entity.Sale;
import lk.ijse.rj.entity.SaleItem;

/**
 *
 * @author Ilman Iqbal
 */
public interface SaleItemDAO extends CrudDAO<SaleItem, String> {
    public List<SaleItem> getSaleItemBySale(Sale sale) throws Exception;
}
