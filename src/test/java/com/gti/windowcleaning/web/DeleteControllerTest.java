package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.storage.StorageI;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.controller.DeleteController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeleteControllerTest {
    StorageI storage;
    @Before
    public void setUp()  {
        storage = Util.getTestDB();
    }

    @Test
    public void deleteItem() throws JSONException {
        EmptyPayload ep = new EmptyPayload();
        Model<Job> model = new Model<>(Job.class, storage);
        DeleteController controller = new DeleteController(model);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(":id", "3");

        Answer answer = controller.process(ep, urlParams, Collections.emptyMap(), false);

        Assert.assertEquals(200, answer.getCode());
        Assert.assertEquals(9, model.getAll().size());
        JSONAssert.assertEquals("{\"success\": \"Item 3 was deleted\"}", answer.getBody(), false);

        answer = controller.process(ep, urlParams, Collections.emptyMap(), false);

        Assert.assertEquals(400, answer.getCode());
        JSONAssert.assertEquals("{\"error\": \"Item not found\"}", answer.getBody(), false);
    }

}
