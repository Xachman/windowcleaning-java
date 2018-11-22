package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import com.gti.windowcleaning.storage.StorageI;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.gti.windowcleaning.web.controller.EntityController;
import org.skyscreamer.jsonassert.JSONAssert;

public class EntityControllerTest {
    StorageI storage;
    @Before
    public void setUp()  {
        storage = Util.getTestDB();
    }
    @Test
    public void getCustomer() throws IOException, JSONException, URISyntaxException {
        EmptyPayload ep = new EmptyPayload();
        Model<Customer> model = new Model<>(Customer.class, storage);
        com.gti.windowcleaning.web.controller.EntityController controller = new EntityController(model);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(":id", "1");
        Answer<String> answer = controller.process(ep, urlParams, Collections.emptyMap(), false);
        String expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customer_expect.json").toURI()), "UTF-8");
        Assert.assertEquals(200, answer.getCode());
        JSONAssert.assertEquals(expect, answer.getBody(), false);

    }
}
