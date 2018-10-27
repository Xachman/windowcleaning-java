/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    }

    public SQLiteStorage() {
    }
    
    private JdbcConnectionSource getConnectionSrource() throws SQLException {
        String databaseUrl = databaseUrl();
        return new JdbcConnectionSource(databaseUrl);
    }
    @Override
    public <T> List<T> get(Class<T> clazz) {
        try { 
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao =  getDao(conn, clazz);

            List<T> list=  dao.queryForAll();
            
            conn.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public <T> T get(Class<T> clazz, int id) {
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, clazz);
            T result = (T) dao.queryForId(id);
            conn.close();
            if(result == null) {
                throw new NoSuchElementException("No element with "+id+" found");
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new NoSuchElementException("No element with "+id+" found");
    }

    @Override
    public <T> List<T> getByField(Class<T> clazz, String fieldName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T add(T object) {
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, object.getClass());
            T item = object;
            dao.create(item);
            conn.close();
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean remove(Class clazz, int id) {
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, clazz);
            int result = dao.deleteById(id);
            conn.close();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <T> List<T> getSort(Class<T> clazz, String field, boolean desending) {
        return null;
    }

    @Override
    public <T> List<T> getRangeSort(Class<T> clazz, long start, long end, String field, boolean ascending) {
        List<T> result = new ArrayList<>();
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, clazz);
            QueryBuilder qb = dao.queryBuilder();
            qb.offset(start);
            qb.limit(end-start);
            qb.orderBy(field, ascending);
            PreparedQuery<T> pq = qb.prepare();
            result = dao.query(pq);
            conn.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public <T> T update(T object) {
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, object.getClass());
            T item = object;
            dao.update(item);
            conn.close();
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    @Override
    public void create(Class clazz) {
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            System.out.println(conn.getUrl());
            TableUtils.createTable(getConnectionSrource(), clazz);
            conn.close();
        } catch(SQLException e) {

        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private <D> Dao<D, Integer> getDao(JdbcConnectionSource conn, Class<D> clazz) throws SQLException {
            return DaoManager.createDao(conn, clazz);
    } 

   @Override
   public <T> List<T> getRange(Class<T> clazz, long start, long end) {
        List<T> result = new ArrayList<>();
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, clazz);
            QueryBuilder qb = dao.queryBuilder();
            qb.offset(start);
            qb.limit(end-start);
            PreparedQuery<T> pq = qb.prepare();
            result = dao.query(pq);
            conn.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
   }

   @Override
   public <T> long getTotalCount(Class<T> clazz) {
        try {
            JdbcConnectionSource conn = getConnectionSrource();
            Dao dao = getDao(conn, clazz);
            long count = dao.countOf();
            conn.close();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
    
}
