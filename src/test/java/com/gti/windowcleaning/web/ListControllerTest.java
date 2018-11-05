/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.storage.StorageI;
import com.gti.windowcleaning.model.JobsModel;
import com.gti.windowcleaning.web.controller.ListController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

/**
 *
 * @author xach
 */
public class ListControllerTest {
    StorageI storage;
    @Before
    public void setUp()  {
        storage = Util.getTestDB();
    }
    @Test
    public void listJobs() throws JSONException, URISyntaxException, IOException {
        JobsModel model = new JobsModel(storage);
        EmptyPayload payload = new EmptyPayload();
        
        ListController controller = new ListController(model);
        Answer answer = controller.process(payload, Collections.emptyMap(), Collections.emptyMap(), false);
        
        String actual = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_expect.json").toURI()), "UTF-8");
        Assert.assertEquals("1-10/10", answer.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(actual, answer.getBody(), STRICT);
       
        Map<String,String> query = new HashMap<>();
        query.put("range", "[2,5]");
        Answer answer2 = controller.process(payload, Collections.emptyMap(), query, false);
       
        String actual2 = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_expect2.json").toURI()), "UTF-8");
        Assert.assertEquals("3-6/10", answer2.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(actual2, answer2.getBody(), STRICT);
    }
    @Test
    public void sortJobs() throws JSONException, URISyntaxException, IOException {
        JobsModel model = new JobsModel(storage);
        EmptyPayload payload = new EmptyPayload();

        ListController controller = new ListController(model);

        Map<String,String> query = new HashMap<>();
        query.put("range", "[0,2]");
        query.put("sort", "[\"serviceDate\", \"DESC\"]");
        Answer answer = controller.process(payload, Collections.emptyMap(), query, false);

        String actual = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_sort_expect.json").toURI()), "UTF-8");
        Assert.assertEquals("1-3/10", answer.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(actual, answer.getBody(), STRICT);

        Map<String,String> query2 = new HashMap<>();
        query2.put("range", "[0,2]");
        query2.put("sort", "[\"serviceDate\", \"ASC\"]");
        Answer answer2 = controller.process(payload, Collections.emptyMap(), query2, false);

        String actual2 = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_sort_ASC_expect.json").toURI()), "UTF-8");
        Assert.assertEquals("1-3/10", answer2.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(actual2, answer2.getBody(), STRICT);
    }
    @Test
    public void between() throws JSONException, URISyntaxException, IOException {
        JobsModel model = new JobsModel(storage);
        EmptyPayload payload = new EmptyPayload();

        ListController controller = new ListController(model);

        Map<String,String> query = new HashMap<>();
        query.put("sort", "[\"serviceDate\", \"ASC\"]");
        query.put("between", "{\"serviceDate\": [1401336000000, 1419570000000]}");
        Answer answer = controller.process(payload, Collections.emptyMap(), query, false);

        String actual = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_between_expect.json").toURI()), "UTF-8");
        JSONAssert.assertEquals(actual, answer.getBody(), STRICT);

    }

    @Test
    public void filter() throws JSONException, URISyntaxException, IOException {
        JobsModel model = new JobsModel(storage);
        EmptyPayload payload = new EmptyPayload();

        ListController controller = new ListController(model);

        Map<String,String> query = new HashMap<>();
        query.put("sort", "[\"id\", \"DESC\"]");
        query.put("filter", "{\"q\": \"JIM\"}");

        Map<String, String> urlParam = new HashMap<>();
        urlParam.put(":field", "servicedBy");
        Answer answer = controller.process(payload, urlParam, query, false);

        String actual = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_filter_expect.json").toURI()), "UTF-8");
        JSONAssert.assertEquals(actual, answer.getBody(), STRICT);
    }
}
