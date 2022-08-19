package com.jetbovapi.domain.animal.service;

import com.jetbovapi.domain.animal.AnimalRepository;
import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalSchemaValidation {

    private AnimalRepository animalRepository;

    public AnimalSchemaValidation(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void validate(Animal animal) throws Exception {
        if (animal.getEarring() == null || animal.getEarring().isBlank()) {
            throw new BusinessException("Invalid earring!");
        }

        if (animal.getWeight() == null || animal.getWeight() <= 0) {
            throw new BusinessException("Invalid weight!");
        }

        for (Animal getAnimal : animalRepository.getAll()) {
            if (animal.getEarring().equals(getAnimal.getEarring())) {
                throw new BusinessException("Existent earring!");
            }
        }
    }

    public void validateEarrings(List<String> animals) throws Exception {
        for (String earring : animals) {
            Animal animal = animalRepository.getByEarring(earring);
            if (animal == null) {
                throw new BusinessException(String.format("The animal %s does not exist!", earring));
            }
        }
    }
}
