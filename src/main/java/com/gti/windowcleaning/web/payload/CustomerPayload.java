/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.payload;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.web.ValidI;
import lombok.Data;

/**
 *
 * @author xach
 */
@Data
public class CustomerPayload implements ValidI{
    private Customer customer; 

    @Override
    public boolean isValid() {
        return customer != null;
    }
}
