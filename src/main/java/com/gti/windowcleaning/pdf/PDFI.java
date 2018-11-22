package com.gti.windowcleaning.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public interface PDFI {
    public String getText();
    public AutoCloseable getStream();
    public ByteArrayOutputStream process();
    public void setData(Map<String,Object> data);
}
