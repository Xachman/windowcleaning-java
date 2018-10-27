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
     void create(Class clazz);
     <T> List<T> get(Class<T> clazz);
     <T> T get(Class<T> clazz, int id);
     <T> List<T> getByField(Class<T> clazz, String fieldName);
     <T>  T add(T object);
     <T>  T update(T object);
     <T>  List<T> add(Class<T> clazz, List<T> object);
     <T>  List<T> getRange(Class<T> clazz, long start, long end);
     <T> long getTotalCount(Class<T> clazz);
     boolean remove(Class clazz, int id);

     <T>  List<T> getSort(Class<T> clazz, String field, boolean desending);

     <T> List<T> getRangeSort(Class<T> clazz, long start, long end, String field, boolean ascending);
}
