package com.jetbovapi.domain.movement.service;

import com.jetbovapi.domain.animal.AnimalRepository;
import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.area.AreaRepository;
import com.jetbovapi.domain.area.model.Area;
import com.jetbovapi.domain.exception.BusinessException;
import com.jetbovapi.domain.movement.model.Movement;
import com.jetbovapi.domain.movement.model.MovementDTO;
import com.jetbovapi.domain.movement.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {
    private MovementRepository movementRepository;
    private AreaRepository areaRepository;
    private AnimalRepository animalRepository;

    public MovementService(MovementRepository movementRepository, AreaRepository areaRepository,
                           AnimalRepository animalRepository) {
        this.movementRepository = movementRepository;
        this.areaRepository = areaRepository;
        this.animalRepository = animalRepository;
    }

    public Movement saveMovement(MovementDTO movementDTO) throws BusinessException {
        this.validateMovement(movementDTO);
        this.moveAnimals(movementDTO.getAnimals());

        Movement movement = new Movement();
        movement.setAnimals(
                movementDTO.getAnimals().stream()
                        .map(earring -> animalRepository.getByEarring(earring))
                        .toList());
        movement.setArea(areaRepository.getByName(movementDTO.getArea()));
        movement.setDays(movementDTO.getDays());

        return movementRepository.save(movement);
    }

    public List<Movement> getAllMovements() {
        return movementRepository.getAll();
    }

    public void validateMovement(MovementDTO movementDTO) throws BusinessException {
        if (movementDTO.getArea() == null || movementDTO.getArea().isBlank()) {
            throw new BusinessException("Invalid area!");
        }

        Area area = areaRepository.getByName(movementDTO.getArea());
        if (area == null) {
            throw new BusinessException("The area does not exist!");
        }

        if (movementDTO.getDays() == null || movementDTO.getDays() <= 0) {
            throw new BusinessException("Days must be greater than 0!");
        }

        if (movementDTO.getAnimals() == null || movementDTO.getAnimals().isEmpty()) {
            throw new BusinessException("Invalid animals!");
        }

        for (String earring : movementDTO.getAnimals()) {
            if (earring.isBlank()) {
                throw new BusinessException("Invalid earring!");
            }

            Animal animal = animalRepository.getByEarring(earring);
            if (animal == null) {
                throw new BusinessException(String.format("The animal %s does not exist!", earring));
            }

            Movement movement = movementRepository.getByAnimal(animal);
            if (movement != null) {
                if (movement.getArea().equals(area)) {
                    throw new BusinessException(String.format("The animal %s is already in this area!", earring));
                }
            }
        }

        List<Movement> movementsList = movementRepository.getWithSameArea(area);
        int total = movementDTO.getAnimals().size();
        for (Movement movement : movementsList) {
            total += movement.getAnimals().size();
        }

        if (area.getMaxQuantity() < total) {
            throw new BusinessException("The limit of animals in this area exceeded!");
        }
    }

    public void moveAnimals(List<String> animals) {
        for (String earring : animals) {
            Animal animal = animalRepository.getByEarring(earring);
            Movement movement = movementRepository.getByAnimal(animal);

            if (movement != null) {
                Animal animalToRemove = movement.getAnimals().stream().filter(a -> a.getEarring().equals(animal.getEarring())).findAny().orElse(null);
                if (animalToRemove != null) {
                    movementRepository.removeAnimal(movement, animalToRemove);
                    animalToRemove.setWeight(animalToRemove.getWeight() + (movement.getArea().getGmd() * movement.getDays()));
                }
            }
        }
    }
}
