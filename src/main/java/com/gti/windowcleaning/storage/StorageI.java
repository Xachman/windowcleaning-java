/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.storage;

import java.util.List;

/**
 *
 * @author xach
 */
public interface StorageI {
     void create(Class clazz);
     void drop(Class clazz);
     <T> List<T> get(Class<T> clazz);
     <T> T get(Class<T> clazz, int id);
     <T>  T add(T object);
     <T>  T update(T object);
     <T>  List<T> add(Class<T> clazz, List<T> object);
     <T>  List<T> getRange(Class<T> clazz, long start, long end);
     <T> long getTotalCount(Class<T> clazz);
     boolean remove(Class clazz, int id);
    <T> List<T> execute(Class<T> clazz, QueryBuilder qb);
}
