package com.jetbovapi.domain.area.service;

import com.jetbovapi.domain.exception.BusinessException;
import com.jetbovapi.domain.area.model.Area;
import com.jetbovapi.domain.area.AreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {
    private AreaRepository areaRepository;
    private AreaSchemaValidation areaSchemaValidation;

    public AreaService(AreaRepository areaRepository, AreaSchemaValidation areaSchemaValidation) {
        this.areaRepository = areaRepository;
        this.areaSchemaValidation = areaSchemaValidation;
    }

    public void saveArea(Area area) throws BusinessException {
        areaSchemaValidation.validate(area);
        areaRepository.save(area);
    }

    public List<Area> getAllAreas() {
        return areaRepository.getAll();
    }
}
