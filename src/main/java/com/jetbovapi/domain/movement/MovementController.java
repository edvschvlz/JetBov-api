package com.jetbovapi.domain.movement;

import com.jetbovapi.domain.movement.model.Movement;
import com.jetbovapi.domain.movement.model.MovementDTO;
import com.jetbovapi.domain.movement.service.MovementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class MovementController {
    private MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Movement> addMovement(@RequestBody MovementDTO movementDTO) {
        try {
            Movement movement = movementService.saveMovement(movementDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(movement);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Movement> findAllMovements() throws Exception {
        return movementService.getAllMovements();
    }
}
