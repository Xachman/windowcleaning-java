/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xach
 */
public class SQLiteStorage implements StorageI {
    private String path; 
    public SQLiteStorage(String path) {
        this.path = path;
        setup();
    }

    public SQLiteStorage() {
        setup();
    }
    
    public void setup() {
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            System.out.println(conn.getUrl());
            TableUtils.createTable(getConnectionSrource(), Customer.class);
            conn.close();
        } catch(SQLException e) {

        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private JdbcConnectionSource getConnectionSrource() throws SQLException {
        String databaseUrl = databaseUrl();
        return new JdbcConnectionSource(databaseUrl);
    }
    @Override
    public <T> List<T> get(Class<T> clazz) {
        if(clazz.equals(Customer.class)) {
            return (List<T>) getCustomers();
        }
        return null;
    }

    @Override
    public <T> T get(Class<T> clazz, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> getByField(Class<T> clazz, String fieldName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> add(Object object) {
        try {
            List<T> result = new ArrayList<>();
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, object.getClass());
            T item = (T) object;
            dao.create(item);
            result.add(item);
            conn.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void remove(Class clazz, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String databaseUrl() {
        if(path == null) {
            return "jdbc:sqlite:"+System.getProperty("user.dir")+"/sqlitetest.db";
        }
        return "jdbc:sqlite:"+path;
    }

    @Override
    public <T> List<T> add(Class<T> clazz, List<T> objects) {
         
        try {
            List<T> result = new ArrayList<>();
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, clazz);
            for(Object object: objects) {
                T item = (T) object;
                dao.create(item);
                result.add(item);
            }
            conn.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private <T> Dao<T, Integer> getDao(JdbcConnectionSource conn, Class<T> clazz) throws SQLException {
            return DaoManager.createDao(conn, clazz);
    } 
   private Dao getCustomerDao() {
        return null;
   } 

   private Customer addCustomer(Customer customer) {
        try { 
            JdbcConnectionSource conn = getConnectionSrource();
            Dao<Customer, Integer> dao =  DaoManager.createDao(conn, Customer.class);

            
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
   }

   private List<Customer> getCustomers() {

        try { 
            JdbcConnectionSource conn = getConnectionSrource();
            Dao<Customer, Integer> dao =  DaoManager.createDao(conn, Customer.class);

            List<Customer> customers =  dao.queryForAll();
            
            conn.close();
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

   }
    
}
