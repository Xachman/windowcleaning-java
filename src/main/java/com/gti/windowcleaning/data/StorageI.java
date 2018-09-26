/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import java.util.List;

/**
 *
 * @author xach
 */
public interface StorageI {
    public <T> List<T> get(Class<T> clazz);
    public <T> T get(Class<T> clazz, int id);
    public <T> List<T> getByField(Class<T> clazz, String fieldName);
    public <T>  List<T> add(Object object); 
    public <T>  List<T> add(Class<T> clazz, List<Object> object); 
    public void remove(Class clazz, int id);
}
