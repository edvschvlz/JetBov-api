package com.jetbovapi.domain.movement;

import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.area.model.Area;
import com.jetbovapi.domain.movement.model.Movement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovementRepository {
    private final List<Movement> movementList = new ArrayList<>();

    public Movement save(Movement movement) {
        movementList.add(movement);
        return movement;
    }

    public List<Movement> getAll() {
        return movementList;
    }

    public Movement getByAnimal(Animal animal) {
        return movementList.stream()
                .filter(movement -> movement.getAnimals().contains(animal))
                .findAny()
                .orElse(null);
    }

    public List<Movement> getWithSameArea(Area area) {
        return movementList.stream()
                .filter(movement -> movement.getArea() == area)
                .toList();
    }

    public void removeIfIsEmpty(Movement movement) {
        if (movement.getAnimals().isEmpty()) {
            movementList.remove(movement);
        }
    }

    public void removeAnimal(Movement movement, Animal animal) {
        List<Animal> animals = new ArrayList<>();
        animals.addAll(movement.getAnimals());
        animals.remove(animal);
        movement.setAnimals(animals);
        removeIfIsEmpty(movement);
    }
}
