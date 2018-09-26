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
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author xach
 */
public class SQLiteStorage implements StorageI {

    public void setup() throws SQLException {
        try {
            String databaseUrl = "jdbc:sqlite:"+System.getProperty("user.dir")+"/sqlitetest.db";
            JdbcConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);
            Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            TableUtils.createTable(connectionSource, Customer.class);
        } catch(SQLException e) {

        }
    }

    @Override
    public <T> List<T> get(Class<T> clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Class clazz, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
