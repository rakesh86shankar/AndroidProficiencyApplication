package com.example.demo.demoapplication.modal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RA283478 on 2/15/2018.
 */

public class NewsList {


    private String title;
    private List<Row> rows = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public List<Row> getRows() {
        return rows;
    }


    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}