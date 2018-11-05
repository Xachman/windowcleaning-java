package com.gti.windowcleaning.storage.querybuilder;

public class Between {
    String field;
    Object value1;
    Object value2;

    public Between(String field, Object value1, Object value2) {
        this.field = field;
        this.value1 = value1;
        this.value2 = value2;
    }
    public String getField() {
        return field;
    }


    public Object getValue1() {
        return value1;
    }


    public Object getValue2() {
        return value2;
    }


}
