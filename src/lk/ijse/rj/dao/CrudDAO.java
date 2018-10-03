/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ilman Iqbal
 */
public interface CrudDAO<T, XID> extends SuperDAO {
    public void insert(T entity) throws Exception;
    
    public void update(T entity) throws Exception;
    
    public void delete(XID id) throws Exception;
    
    public T find(XID id) throws Exception;
    
    public List<T> getAll() throws Exception;
}
