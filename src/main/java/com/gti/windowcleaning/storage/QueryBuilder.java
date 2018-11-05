package com.gti.windowcleaning.storage;

import com.gti.windowcleaning.storage.querybuilder.Range;
import com.gti.windowcleaning.storage.querybuilder.Filter;
import com.gti.windowcleaning.storage.querybuilder.Between;
import com.gti.windowcleaning.storage.querybuilder.Sort;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private Range range;
    private List<Filter> filters = new ArrayList<>();
    private List<Between> between = new ArrayList<>();
    private Sort sort;

    public Range getRange() {
        return range;
    }

    public void setRange(long start, long end) {
        this.range = new Range(start, end);
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(String field, boolean ascending) {
        this.sort = new Sort(field,ascending);
    }

    public void addFilter(String field, Object value) {
        filters.add(new Filter(field, value));
    }

    public void addBetween(String field, Object value1, Object value2) {
        between.add(new Between(field, value1, value2));
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public List<Between> getBetween() {
        return between;
    }

}
