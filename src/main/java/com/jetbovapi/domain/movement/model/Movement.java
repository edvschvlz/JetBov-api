package com.jetbovapi.domain.movement.model;

import java.util.List;

import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.area.model.Area;

public class Movement {
    private List<Animal> animals;
    private Area area;
    private Integer days;

    public Movement(List<Animal> animals, Area area, Integer days) {
        this.animals = animals;
        this.area = area;
        this.days = days;
    }

    public Movement() {

    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
