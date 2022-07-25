package com.jetbovapi.domain.animal;

import com.jetbovapi.domain.animal.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {
    private final List<Animal> animalList = new ArrayList<>();

    public void save(Animal a) {
        Animal animal = new Animal();
        animal.setEarring(a.getEarring());
        animal.setWeight(a.getWeight());
        animalList.add(animal);
    }

    public List<Animal> getAll() {
        return animalList;
    }

    public Animal getByEarring(String earring) {
        return animalList.stream()
                .filter(a -> earring.equals(a.getEarring()))
                .findAny()
                .orElse(null);
    }

    public List<Animal> getAllSpecified(List<String> animals) {
        return animals.stream()
                .map(a -> getByEarring(a))
                .toList();
    }
}
