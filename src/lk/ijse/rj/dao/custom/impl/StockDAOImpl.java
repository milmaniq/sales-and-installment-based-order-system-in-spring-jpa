/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lk.ijse.rj.dao.CrudDAOImpl;
import lk.ijse.rj.dao.custom.StockDAO;
import lk.ijse.rj.entity.Stock;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ilman Iqbal
 */

@Component
public class StockDAOImpl extends CrudDAOImpl<Stock, String> implements StockDAO {


}
