/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.web.controller.CustomersController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class CustomersControllerTest {
    private StorageI storage; 
    @Before
    public void setUp() {
        URL dbPath =  getClass().getResource("/mocks/test.db");
        File file = new File(dbPath.getPath());
        boolean file_exists = file.exists();
        if(file_exists) {
            file.delete();
        }
        storage = new SQLiteStorage(dbPath.toString());
        storage.create(Customer.class);
        storage.add(Customer.class, Util.assembleCustomers());
    }
    @Test
    public void displayCustomers() {
        EmptyPayload ep = new EmptyPayload();

        CustomersModel model = new CustomersModel(storage);
        CustomersController controller = new CustomersController(model);
        
        Answer answer = controller.process(ep, Collections.emptyMap(), true);
        assertEquals(true, false);
    }
}
