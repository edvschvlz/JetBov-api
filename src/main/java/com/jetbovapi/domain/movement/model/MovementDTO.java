package com.jetbovapi.domain.movement.model;

import java.util.List;

public class MovementDTO {
    private List<String> animals;
    private String area;
    private Integer days;

    public MovementDTO(List<String> animals, String area, Integer days) {
        this.animals = animals;
        this.area = area;
        this.days = days;
    }

    public MovementDTO() {

    }

    public List<String> getAnimals() {
        return animals;
    }

    public void setAnimals(List<String> animals) {
        this.animals = animals;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
