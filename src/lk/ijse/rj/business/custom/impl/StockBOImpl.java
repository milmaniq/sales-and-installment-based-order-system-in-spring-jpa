/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dao.custom.QueryDAO;
import lk.ijse.rj.dao.custom.StockDAO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.entity.Stock;
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
public class StockBOImpl implements StockBO{

    @Autowired
    private StockDAO stockDAO;

    @Autowired
    private QueryDAO queryDAO;


    public StockBOImpl() {
    }


    @Override
    public ArrayList<StockDTO> getAvailableStocks() throws Exception {
        try {

            List<Stock> stocks = queryDAO.getAvailableStockDetail();
            ArrayList<StockDTO> stockDTOs = new ArrayList<>();
            for (Stock stock : stocks) {
                StockDTO stockDTO = new StockDTO(stock.getItemId(), stock.getDescription(),
                        stock.getWeight(), stock.getRate(), stock.getDateAdded());
                stockDTOs.add(stockDTO);
            }

            return stockDTOs;
        } catch (HibernateException ex) {
            return null;
        }

    }
    

    
    @Override
    public StockDTO findStock(String id) throws Exception {
        try {

            Stock stock = stockDAO.find(id);
            if (stock == null) {
                return null;
            }
            StockDTO stockDTO = new StockDTO(stock.getItemId(), stock.getDescription(), stock.getWeight(),
                    stock.getRate(), stock.getDateAdded());

            return stockDTO;
        } catch (HibernateException ex) {
            return null;
        }
        

    }

    
    ////////////checked upto here

    @Override
    public boolean insertStock(StockDTO dto) throws Exception {

        try {

            Stock stock = new Stock(dto.getItemId(), dto.getDescription(), dto.getWeight(), dto.getRate(), dto.getDateAdded());
            stockDAO.insert(stock);

            return true;
        }
        catch (HibernateException ex) {
            return false;
        }

    }

    @Override
    public boolean updateStock(StockDTO dto) throws Exception {
        try {

            Stock stock = new Stock(dto.getItemId(), dto.getDescription(), dto.getWeight(), dto.getRate(), dto.getDateAdded());
            stockDAO.update(stock);

            return true;
        } catch (HibernateException ex) {
            return false;
        }

    }

    @Override
    public boolean deleteStock(String id) throws Exception {
        try {

            stockDAO.delete(id);

            return true;
        } catch (HibernateException ex) {
            return false;
        }

    }

    @Override
    public ArrayList<StockDTO> getAllStocks() throws Exception {
        try {

            List<Stock> all = stockDAO.getAll();
            ArrayList<StockDTO> allStocks = new ArrayList<>();
            for (Stock stock : all) {
                StockDTO stockDTO = new StockDTO(stock.getItemId(), stock.getDescription(), stock.getWeight(), stock.getRate(), stock.getDateAdded());
                allStocks.add(stockDTO);
            }
            System.out.println("before commit: " + allStocks);

            System.out.println("after commit: " + allStocks);
            return allStocks;
        } catch (HibernateException ex) {
            return null;
        }

    }

    
    

   
}
