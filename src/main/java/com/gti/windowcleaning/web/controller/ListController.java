package com.gti.windowcleaning.web.controller;

import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.storage.QueryBuilder;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListController extends Controller<EmptyPayload> {
    private Model model;

    public ListController(Model model) {
        super(EmptyPayload.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        JSONParser parser = new JSONParser();
        Map<String, String> headers = new HashMap<>();
        try {
            QueryBuilder qb = new QueryBuilder();
            if(urlParams.get(":field") != null || query.get("filter") != null) {
                String field = urlParams.get(":field");
                JSONObject filter = (JSONObject) parser.parse(query.get("filter"));
                if(filter.size() > 0) {
                    for(Object key: filter.keySet()) {
                        if(key.toString().equals("q")) {
                            String q = filter.get("q").toString();
                            qb.addFilter(field, q);
                            continue;
                        }
                        qb.addFilter(key.toString(), filter.get(key));
                    }

                }
            }
            if(query.get("range") != null) {
                JSONArray range = (JSONArray) parser.parse(query.get("range"));
                long start = (long) range.get(0);
                long end = (long) range.get(1)+1;
                qb.setRange(start, end);
                headers.put("Content-Range", (start+1)+"-"+(end)+"/"+model.getTotalCount());
            }
            if(query.get("sort") != null) {
                JSONArray sort = (JSONArray) parser.parse(query.get("sort"));
                String ASC = sort.get(1).toString();
                qb.setSort(sort.get(0).toString(), (ASC.equals("ASC"))? true: false);
            }
            if(query.get("between") != null) {
                JSONObject between = (JSONObject) parser.parse(query.get("between"));
                String field = between.keySet().iterator().next().toString();
                JSONArray values = (JSONArray) between.values().iterator().next();
                Object value1 = model.processField(field, values.get(0));
                Object value2 = model.processField(field,values.get(1));
                qb.addBetween(field, value1, value2);
            }
            List result = model.execute(qb);
            if(headers.get("Content-Range") == null) {
                headers.put("Content-Range", "1-"+model.getTotalCount()+"/"+model.getTotalCount());
            }
            String json = dataToJson(result);
            return new Answer(200, json, headers);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return new Answer(500, "{\"error\": \"Something wrong with request\"}");
    }

}
