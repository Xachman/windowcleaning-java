/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import java.util.Map;

/**
 *
 * @author xach
 */
public class Answer {
    private Map<String,String> headers;
    public Answer(int code) {
        this.code = code;
        this.body = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (code != answer.code) return false;
        if (body != null ? !body.equals(answer.body) : answer.body != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer(code=" + code + ", body=" + body + ")";
    }

    public String getBody() {
        return body;
    }

    public int getCode() {
        return code;
    }

    private int code;
    private String body;

    public Answer(int code, String body){
        this.code = code;
        this.body = body;
    }

    public Answer(int code, String body, Map<String,String> headers) {
        this(code, body);
        this.headers = headers;
    }

    public static Answer ok(String body) {
        return new Answer(200, body);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
