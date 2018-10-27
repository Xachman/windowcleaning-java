package com.gti.windowcleaning.model.execute.options;

public class Sort {
    String field;
    boolean desending;

    public Sort(String field, boolean desending) {
        this.field = field;
        this.desending = desending;
    }

    public Sort(String field) {
        this.field = field;
        this.desending = true;
    }

    public String getField() {
        return field;
    }

    public boolean isDesending() {
        return desending;
    }
}
