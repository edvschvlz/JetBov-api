package com.jetbovapi.domain.animal.model;

public class Animal {
    private String earring;
    private Double weight;

    public Animal(String earring, Double weight) {
        this.earring = earring;
        this.weight = weight;
    }

    public Animal() {

    }

    public String getEarring() {
        return earring;
    }

    public void setEarring(String earring) {
        this.earring = earring;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
