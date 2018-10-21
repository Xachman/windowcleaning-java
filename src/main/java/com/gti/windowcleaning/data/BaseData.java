/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import com.gti.windowcleaning.web.ValidI;
import com.j256.ormlite.field.DatabaseField;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author xach
 */
public class BaseData implements ValidI{
    @Ignore
    private boolean valid;

    @Override
    public boolean isValid() {
        return requiredFieldsSet();
    }

    private boolean requiredFieldsSet() {
        for(Field field: getClass().getDeclaredFields()) {
            DatabaseField dbField = field.getAnnotation(DatabaseField.class);
            if(dbField != null) {
                if(!dbField.canBeNull() && !fieldIsSet(field, this)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean fieldIsSet(Field field,  Object object) {
        try {
            if(field.getType().equals(boolean.class)){
                Method getMethod = object.getClass().getMethod("is"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
                return checkMethod(getMethod, object);
            }
            Method getMethod = object.getClass().getMethod("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
            return checkMethod(getMethod, object);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
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
}
