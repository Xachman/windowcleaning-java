/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.valid;

import com.gti.windowcleaning.web.ValidI;

/**
 *
 * @author xach
 */
public class EmptyPayload implements ValidI {
    @Override
    public boolean isValid() {
        return true;
    }
}