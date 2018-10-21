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
    public void create(Class clazz);
    public <T> List<T> get(Class<T> clazz);
    public <T> T get(Class<T> clazz, int id);
    public <T> List<T> getByField(Class<T> clazz, String fieldName);
    public <T>  T add(T object); 
    public <T>  T update(T object); 
    public <T>  List<T> add(Class<T> clazz, List<T> object); 
    public <T>  List<T> getRange(Class<T> clazz, long start, long end); 
    public <T> long getTotalCount(Class<T> clazz);
    public boolean remove(Class clazz, int id);
}
