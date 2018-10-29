/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.execute.ExecuteOptions;
import com.gti.windowcleaning.model.execute.options.Range;
import com.gti.windowcleaning.model.execute.options.Sort;
import com.j256.ormlite.field.DatabaseField;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xach
 */
public abstract class Model<T> {
    protected StorageI storage;
    protected Class<T> clazz;
    public Model(Class<T> clazz) {
        this(new SQLiteStorage(System.getProperty("user.home")+File.separator+".windowcleaning"+File.separator+"data.db"), clazz);
    }
    
    public Model(StorageI storage, Class<T> clazz) {
        this.clazz = clazz;

        this.storage = storage;
    }

    public T save(T object) throws MustIncludeException {
        validate(object);
        return storage.add(object);
    }
    
    public List<T> getAll() {
        return storage.get(clazz);
    }

    public T get(int id) {
        return storage.get(clazz, id);
    }
    public boolean remove(int id) {
        return storage.remove(clazz, id);
    }
    public T update(T object) {
        return storage.update(object);
    }
    public List<T> getRange(long start, long end) {
        return storage.getRange(clazz, start, end); 
    }
    public long getTotalCount() {
        return storage.getTotalCount(clazz);
    }
    private void validate(Object object) throws MustIncludeException {
        Class<?> objectClass = Objects.requireNonNull(object).getClass();
        List<String> errorFields =  new ArrayList<>();       
        for(Field field: objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(DatabaseField.class)) {
                DatabaseField dbField = (DatabaseField) field.getAnnotation(DatabaseField.class);
                if(!dbField.canBeNull() && !fieldIsSet(field, object)) {
                    errorFields.add(field.getName());
                }
            }
        }

        if(errorFields.size() > 0) {
            System.out.println(errorFields.toString());
            throw new MustIncludeException(errorFields.toString());
        }
    } 
    public List<T> execute(ExecuteOptions executeOptions) {
        switch(executeOptions.type()) {
            case ExecuteOptions.RANGE_SORT:
                return storage.getRangeSort(clazz,
                        executeOptions.getRange().getStart(),
                        executeOptions.getRange().getEnd(),
                        executeOptions.getSort().getField(),
                        executeOptions.getSort().isAscending());
            case ExecuteOptions.RANGE:
                return storage.getRange(clazz, executeOptions.getRange().getStart(),executeOptions.getRange().getEnd());
            case ExecuteOptions.SORT:
                return storage.getSort(clazz, executeOptions.getSort().getField(), executeOptions.getSort().isAscending());
            case ExecuteOptions.BETWEEN:
                return storage.getBetween(clazz, executeOptions.getBetween().getField(), executeOptions.getBetween().getValue1(), executeOptions.getBetween().getValue2());
            case ExecuteOptions.BETWEEN_SORT:
                return storage.getBetweenSort(clazz, executeOptions.getBetween().getField(),
                        executeOptions.getBetween().getValue1(),
                        executeOptions.getBetween().getValue2(),
                        executeOptions.getSort().getField(),
                        executeOptions.getSort().isAscending()
                );
        }
        return getAll();
    }
    private boolean fieldIsSet(Field field,  Object object) {
        try {
            if(field.getType().equals(boolean.class)){
                Method getMethod = object.getClass().getMethod("is"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
                return checkMethod(getMethod, object);
            }
            Method getMethod = object.getClass().getMethod("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
            return checkMethod(getMethod, object);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private boolean checkMethod(Method method, Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
       Class<?> returnType = method.getReturnType();
       Object methodValue =  method.invoke(object);
       if(methodValue == null) {
           return false;
       }
       if(returnType.equals(String.class)) {
           String value = (String) methodValue;
           if(value.length() > 0) {
               return true;
           }
       }
       return true;
    }


    public Class<T> getClazz() {
        return clazz;
    }
}
