package com.gti.windowcleaning.model.execute.options;

public class Sort {
    String field;
    boolean ascending;

    public Sort(String field, boolean ascending) {
        this.field = field;
        this.ascending = ascending;
    }

    public Sort(String field) {
        this.field = field;
        this.ascending = true;
    }

    public String getField() {
        return field;
    }

    public boolean isAscending() {
        return ascending;
    }
}
