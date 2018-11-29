package com.gti.windowcleaning.pdf;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleTest {
    Map<String, Object> data = new HashMap<>();
    @Before
    public void setUp() {
        List<Map<String,String>> items = new ArrayList<>();

        Map<String,String> item1 = new HashMap<>();
        item1.put("customer", "Burk, David");
        item1.put("doneBy", "Tim");
        item1.put("location", "Salisbury");
        item1.put("notes", "Some notes about this job this job note happens to be really long so that we can test line wrapping.  Its important to know if we can calculate the lines that are wrapped and where to wrap.");
        item1.put("amount", "600.00");
        item1.put("dateTime", "11/12/2018 8:00AM");
        item1.put("invoice", "123456");
        item1.put("payment", "600.00");
        item1.put("totals", "600.00");
        items.add(item1);

        Map<String,String> item2 = new HashMap<>();
        item2.put("customer", "Williams, Terry");
        item2.put("doneBy", "Tim");
        item2.put("location", "Dover");
        item2.put("dateTime", "11/12/2018 9:00AM");
        item2.put("notes", "Some notes about this job2\nNext line job2 notes\nLine 3 for job2");
        item2.put("invoice", "");
        item2.put("amount", "400.00");
        item2.put("payment", "400.00");
        item2.put("totals", "400.00");
        items.add(item2);

        Map<String,String> item3 = new HashMap<>();
        item3.put("customer", "Hanks, Tom");
        item3.put("doneBy", "Tim");
        item3.put("location", "Stanford");
        item3.put("notes", "Some notes about this job3");
        item3.put("amount", "1600.00");
        item3.put("dateTime", "11/12/2018 10:00AM");
        item3.put("totals", "1600.00");
        item3.put("payment", "");
        item3.put("invoice", "123457");
        items.add(item3);

        data.put("schedule", items);
    }
    @Test
    public void getSchedule() {
        Schedule scheduleGenerator = new Schedule();

        scheduleGenerator.setData(data);
        scheduleGenerator.process();

        String result = scheduleGenerator.getText();

        assertTrue(result.contains("Burk, David"));
        assertTrue(result.contains("Tim"));
        assertTrue(result.contains("Salisbury"));
        assertTrue(result.contains("600.00"));
        assertTrue(result.contains("11/12/2018 8:00AM"));
        assertTrue(result.contains("123456"));
        assertTrue(result.contains("600.00"));
        assertTrue(result.contains("Some notes about this job this job note happens to be really long so that we can test line wrapping.  Its\nimportant to know if we can calculate the lines that are wrapped and where to wrap."));


        assertTrue(result.contains("Williams, Terry"));
        assertTrue(result.contains("Tim"));
        assertTrue(result.contains("Dover"));
        assertTrue(result.contains("11/12/2018 9:00AM"));
        assertTrue(result.contains("Some notes about this job2\nNext line job2 notes\nLine 3 for job2"));
        assertTrue(result.contains("400.00"));
        assertTrue(result.contains("400.00"));


        assertTrue(result.contains("Hanks, Tom"));
        assertTrue(result.contains("Tim"));
        assertTrue(result.contains("Stanford"));
        assertTrue(result.contains("Some notes about this job3"));
        assertTrue(result.contains("1600.00"));
        assertTrue(result.contains("11/12/2018 10:00AM"));
        assertTrue(result.contains("1600.00"));
        assertTrue(result.contains("123457"));
    }
}
