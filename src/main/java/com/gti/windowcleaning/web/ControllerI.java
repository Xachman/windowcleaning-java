/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import java.util.Map;

/**
 *
 * @author xach
 */
public interface ControllerI<V extends ValidI> {
       Answer process(V value, Map<String, String> urlParams, Map<String,String> query, boolean shouldReturnHtml);
}
