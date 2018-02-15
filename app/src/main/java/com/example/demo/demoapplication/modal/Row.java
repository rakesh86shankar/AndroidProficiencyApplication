package com.example.demo.demoapplication.modal;

/**
 * Created by RA283478 on 2/15/2018.
 */


import java.util.HashMap;
import java.util.Map;



public class Row {

    private String title;
    private String description;
    private String imageHref;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getImageHref() {
        return imageHref;
    }


    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}