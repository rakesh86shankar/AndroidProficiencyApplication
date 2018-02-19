package com.example.demo.demoapplication.modal;

import java.util.List;


public class NewsList {


    private String title = null;
    private List<Row> rows = null;

    public String getTitle() {
        return title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}