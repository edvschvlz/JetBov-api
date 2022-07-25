package com.jetbovapi.domain.animal;

import com.jetbovapi.domain.exception.BusinessException;
import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.animal.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/animals")
public class AnimalController {
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        try {
            animalService.saveAnimal(animal);
            return ResponseEntity.status(HttpStatus.CREATED).body(animal);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Animal> findAllAnimals() {
        return animalService.getAllAnimals();
    }

    @RequestMapping(value = "/earrings", method = RequestMethod.GET)
    public ResponseEntity<List<Animal>> findAllAnimalsSpecified(@RequestParam List<String> earring) {
        try {
            List<Animal> animalList = animalService.getAllAnimalsSpecified(earring);
            return ResponseEntity.status(HttpStatus.OK).body(animalList);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
