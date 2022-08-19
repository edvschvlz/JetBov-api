package com.jetbovapi.domain.animal.service;

import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.animal.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;
    private AnimalSchemaValidation animalSchemaValidation;

    public AnimalService(AnimalRepository animalRepository, AnimalSchemaValidation animalSchemaValidation) {
        this.animalRepository = animalRepository;
        this.animalSchemaValidation = animalSchemaValidation;
    }

    public void saveAnimal(Animal animal) throws Exception {
        animalSchemaValidation.validate(animal);
        animalRepository.save(animal);
    }

    public List<Animal> getAllAnimalsSpecified(List<String> animals) throws Exception {
        animalSchemaValidation.validateEarrings(animals);
        return animalRepository.getAllSpecified(animals);
    }

    public List<Animal> getAllAnimals() throws Exception {
        return animalRepository.getAll();
    }
}
