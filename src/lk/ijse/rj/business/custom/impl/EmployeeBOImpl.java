/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.rj.business.custom.EmployeeBO;
import lk.ijse.rj.dao.custom.EmployeeDAO;
import lk.ijse.rj.dto.EmployeeDTO;
import lk.ijse.rj.entity.Employee;
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
public class EmployeeBOImpl implements EmployeeBO{

    @Autowired
    private EmployeeDAO employeeDAO;


    public EmployeeBOImpl() {

    }
    
    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws Exception {
        try {

            List<Employee> employees = employeeDAO.getAll();


            System.out.println(employees);
            ArrayList<EmployeeDTO> employeeDTOs = new ArrayList<>();
            for (Employee employee : employees) {
                EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getName(),
                        employee.getContact(), employee.getAddress());
                employeeDTOs.add(employeeDTO);
            }
            return employeeDTOs;

        }
        catch(HibernateException ex){
            return null;
        }
    }
    
/////////////checked upto here

    
    @Override
    public boolean insertEmployee(EmployeeDTO dto) throws Exception {
        try {


            Employee employee = new Employee(dto.getEmployeeId(), dto.getName(), dto.getAddress(), dto.getContact());
            employeeDAO.insert(employee);

            return true;
        }catch (HibernateException ex){
            return false;
        }


    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws Exception {
        try {


            Employee employee = new Employee(dto.getEmployeeId(), dto.getName(), dto.getAddress(), dto.getContact());
            employeeDAO.update(employee);

            return true;
        }catch (HibernateException ex){
            return false;
        }

    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        try {

            employeeDAO.delete(id);

            return true;
        }catch (HibernateException ex){
            return false;
        }
    }

    

    @Override
    public EmployeeDTO findEmployee(String id) throws Exception {
        try {

            Employee employee = employeeDAO.find(id);

            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getContact(), employee.getAddress());
            return employeeDTO;
        }catch (HibernateException ex){
            return null;
        }
    }

   
}
