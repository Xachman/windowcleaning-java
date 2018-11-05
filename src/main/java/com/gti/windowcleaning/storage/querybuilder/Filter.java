package com.gti.windowcleaning.storage.querybuilder;

public class Filter {
    private String field;
    private Object value;

    public Filter(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
