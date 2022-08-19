package com.jetbovapi.domain.area;

import com.jetbovapi.domain.area.model.Area;
import com.jetbovapi.domain.area.service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Area> addArea(@RequestBody Area area) {
        try {
            areaService.saveArea(area);
            return ResponseEntity.status(HttpStatus.CREATED).body(area);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Area> findAllAreas() throws Exception {
        return areaService.getAllAreas();
    }
}
