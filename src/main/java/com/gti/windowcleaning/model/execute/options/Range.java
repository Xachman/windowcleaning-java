package com.gti.windowcleaning.model.execute.options;

public class Range {
    private long start;
    private long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
