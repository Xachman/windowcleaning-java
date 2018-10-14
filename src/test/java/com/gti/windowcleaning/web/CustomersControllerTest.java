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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resources;
import static junit.framework.Assert.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

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
        List<Customer> customers = Util.assembleCustomers();
        storage.add(customers.get(0));
    }
    @Test
    public void displayCustomers() throws ParseException, FileNotFoundException, IOException, JSONException, URISyntaxException {
        EmptyPayload ep = new EmptyPayload();

        CustomersModel model = new CustomersModel(storage);
        CustomersController controller = new CustomersController(model);
        
        Answer answer = controller.process(ep, Collections.emptyMap(), true);
        String expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customers_expect.json").toURI()), "UTF-8");
        
        JSONAssert.assertEquals(expect, answer.getBody(), false);

    }
}
