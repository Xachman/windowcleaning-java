package com.gti.windowcleaning.model.execute;

import com.gti.windowcleaning.model.execute.options.Between;
import com.gti.windowcleaning.model.execute.options.Range;
import com.gti.windowcleaning.model.execute.options.Sort;

public class ExecuteOptions {
    private Range range;
    private Sort sort;
    private Between between;

    static public final int BETWEEN_SORT = 5;
    static public final int BETWEEN = 4;
    static public final int RANGE_SORT = 3;
    static public final int SORT = 2;
    static public final int RANGE = 1;

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int type() {
        if(between != null && sort != null) {
            return ExecuteOptions.BETWEEN_SORT;
        }
        if(between != null) {
            return ExecuteOptions.BETWEEN;
        }
        if(range != null && sort != null) {
            return ExecuteOptions.RANGE_SORT;
        }
        if(range != null) {
            return ExecuteOptions.RANGE;
        }
        if(sort != null) {
            return ExecuteOptions.SORT;
        }
        return 0;
    }

    public Between getBetween() {
        return between;
    }

    public void setBetween(String field, Object value1, Object value2) {
        between = new Between(field, value1, value2);
    }
}
