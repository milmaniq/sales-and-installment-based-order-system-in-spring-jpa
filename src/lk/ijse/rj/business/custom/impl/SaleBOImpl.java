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

import lk.ijse.rj.business.custom.SaleBO;
import lk.ijse.rj.dao.custom.*;
import lk.ijse.rj.dto.SaleDTO;
import lk.ijse.rj.dto.SaleItemDTO;
import lk.ijse.rj.entity.*;
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
public class SaleBOImpl implements SaleBO {

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private StockDAO stockDAO;

    @Autowired
    private SaleItemDAO saleItemDAO;


    public SaleBOImpl() {
    }


    @Override
    public boolean insertSale(SaleDTO dto) throws Exception {
        try {

            Employee employee = employeeDAO.find(dto.getEmployeeId());
            Order order = null;
            if (dto.getOrderId() != null){
                order = orderDAO.find(dto.getOrderId());

            }
            Sale sale = new Sale(dto.getSalesId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfSales(),
                    dto.getTotal(), dto.getBuyGold(), dto.getNetTotal(), dto.getDateOfCancellation(), employee,
                    order);

            saleDAO.insert(sale);

            List<SaleItemDTO> saleItemDTOs = dto.getSaleItems();
            for (SaleItemDTO saleItemDTO : saleItemDTOs) {
                Stock stock = stockDAO.find(saleItemDTO.getItemId());

                SaleItem saleItem = new SaleItem(stock, saleItemDTO.getDescription(),
                        saleItemDTO.getPrice(), sale);

                saleItemDAO.insert(saleItem);
            }
            if (dto.getOrderId() != null) {
                Order order2 = new Order(dto.getOrderId(), dto.getDateOfSales());

                orderDAO.updateDateOfActualDeliveryByOrderId(order2);
            }

            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<SaleItemDTO> getSaleItemBySaleId(String id) throws Exception {
        try {

            Sale sale = saleDAO.find(id);
            List<SaleItem> saleItems = saleItemDAO.getSaleItemBySale(sale);
            ArrayList<SaleItemDTO> saleItemDTOs = new ArrayList<>();
            for (SaleItem saleItem : saleItems) {
                SaleItemDTO saleItemDTO = new SaleItemDTO(saleItem.getStock().getItemId(), saleItem.getDescription(),
                        saleItem.getPrice());
                saleItemDTOs.add(saleItemDTO);
            }

            return saleItemDTOs;

        } catch (HibernateException ex) {
            return null;
        }

    }

    @Override
    public boolean updateSale(SaleDTO dto) throws Exception {
        try {

            Employee employee = employeeDAO.find(dto.getEmployeeId());

            Order order = null;
            if (dto.getOrderId() != null){
                order = orderDAO.find(dto.getOrderId());
            }

            Sale sale = new Sale(dto.getSalesId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfSales(),
                    dto.getTotal(), dto.getBuyGold(), dto.getNetTotal(), dto.getDateOfCancellation(),
                    employee, order);

            saleDAO.update(sale);

            List<SaleItemDTO> saleItemDTOs = dto.getSaleItems();

//            for (SaleItemDTO saleItemDTO : saleItemDTOs) {
//
//                entityManager.createNamedQuery("updateSaleItem")
//                        .setParameter("itemId",saleItemDTO.getItemId())
//                        .setParameter("description",saleItemDTO.getDescription())
//                        .setParameter("price",saleItemDTO.getPrice())
//                        .executeUpdate();
//            }

            return true;

        } catch (HibernateException ex) {
            return false;
        }
    }

    private ArrayList<SaleDTO> getArrayList(List<Sale> allSales) throws Exception {
        ArrayList<SaleDTO> allSaleDTOs = new ArrayList<>();
        for (Sale sale : allSales) {
            String orderId;
            if (sale.getOrder() == null){
                orderId = null;
            }
            else{
                orderId = sale.getOrder().getOrderId();
            }
            SaleDTO saleDTO = new SaleDTO(sale.getSaleId(), sale.getEmployee().getEmployeeId(),
                    orderId, sale.getName(),
                    sale.getContact(), sale.getDateOfSale(), sale.getAddress(), sale.getTotal(), sale.getBuyGold(),
                    sale.getNetTotal(), sale.getDateOfCancellation());
            allSaleDTOs.add(saleDTO);
        }

        return allSaleDTOs;
    }

    @Override
    public ArrayList<SaleDTO> getSaleBySaleId(String id) throws Exception {
        try {

            List<Sale> sales = saleDAO.getSaleBySaleId(id);

            return getArrayList(sales);
        } catch (HibernateException ex) {
            return null;
        }

    }

    @Override
    public ArrayList<SaleDTO> getSaleByName(String name) throws Exception {
        try {

            List<Sale> sales = saleDAO.getSaleByName(name);

            return getArrayList(sales);
        } catch (HibernateException ex) {
            return null;
        }

    }

    @Override
    public ArrayList<SaleDTO> getSaleByContact(String contact) throws Exception {
        try {

            List<Sale> sales = saleDAO.getSaleByContact(contact);

            return getArrayList(sales);
        } catch (HibernateException ex) {
            return null;
        }

    }

    @Override
    public ArrayList<SaleDTO> getSaleByDateOfSale(String date) throws Exception {
        try {

            List<Object[]> objectList = saleDAO.getSaleByDateOfSale(date);
            List<Sale> sales = new ArrayList<>();

            for (Object[] objects : objectList) {
                Employee employee = employeeDAO.find((String) objects[9]);

                Order order = null;
                if (objects[10] != null){
                    order = orderDAO.find((String) objects[10]);;
                }

                Sale sale = new Sale(
                        (String) objects[0],
                        (String) objects[1],
                        (String) objects[2],
                        (String) objects[3],
                        (Date) objects[4],
                        (BigDecimal) objects[5],
                        (BigDecimal) objects[6],
                        (BigDecimal) objects[7],
                        (Date) objects[8],
                        employee,
                        order);
                sales.add(sale);
            }


            return getArrayList(sales);
        } catch (HibernateException ex) {
            return null;
        }

    }

    @Override
    public ArrayList<SaleDTO> getSaleByOrderId(String orderId) throws Exception {
        try {

            List<Object[]> saleByOrder = saleDAO.getSaleByOrder(orderId);

            List<Sale> sales = new ArrayList<>();

            for (Object[] objects : saleByOrder) {
                Employee employee = employeeDAO.find((String) objects[9]);

                Order order = null;
                if (objects[10] != null){
                    order = orderDAO.find((String) objects[10]);;
                }

                Sale sale = new Sale(
                        (String) objects[0],
                        (String) objects[1],
                        (String) objects[2],
                        (String) objects[3],
                        (Date) objects[4],
                        (BigDecimal) objects[5],
                        (BigDecimal) objects[6],
                        (BigDecimal) objects[7],
                        (Date) objects[8],
                        employee,
                        order);
                sales.add(sale);
            }

            return getArrayList(sales);
        } catch (HibernateException ex) {
            return null;
        }

    }

    ///////////////////checked upto here



    @Override
    public SaleDTO findSale(String id) throws Exception {
        try {
            Sale sale = saleDAO.find(id);
            String orderId;
            if (sale.getOrder() == null){
                orderId = null;
            }
            else{
                orderId = sale.getOrder().getOrderId();
            }
            SaleDTO saleDTO = new SaleDTO(sale.getSaleId(), sale.getEmployee().getEmployeeId(),
                    orderId, sale.getName(),
                    sale.getContact(), sale.getDateOfSale(), sale.getAddress(), sale.getTotal(), sale.getBuyGold(),
                    sale.getNetTotal(), sale.getDateOfCancellation());

            return saleDTO;
        } catch (HibernateException ex) {
            return null;
        }


    }


    @Override
    public ArrayList<SaleDTO> getAllSales() throws Exception {
        try {

            List<Sale> all = saleDAO.getAll();
            System.out.println(all);

            if (all == null) {
                System.out.println("it is null");
                return null;
            }

            return getArrayList(all);

        } catch (HibernateException ex) {
            System.out.println("it is null in catch");
            return null;
        }

    }


}
