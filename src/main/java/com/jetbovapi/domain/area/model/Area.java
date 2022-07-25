package com.jetbovapi.domain.area.model;

public class Area {
    private String name;
    private Integer maxQuantity;
    private Double gmd;

    public Area(String name, Integer maxQuantity, Double gmd) {
        this.name = name;
        this.maxQuantity = maxQuantity;
        this.gmd = gmd;
    }

    public Area() {
    }

    public String getName() {
        return name;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public Double getGmd() {
        return gmd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void setGmd(Double gmd) {
        this.gmd = gmd;
    }
}
