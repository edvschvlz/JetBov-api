package com.jetbovapi.domain.area.service;

import com.jetbovapi.domain.area.AreaRepository;
import com.jetbovapi.domain.area.model.Area;
import com.jetbovapi.domain.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class AreaSchemaValidation {

    private AreaRepository areaRepository;

    public AreaSchemaValidation(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public void validate(Area area) throws BusinessException {
        if (area.getName() == null || area.getName().isBlank()) {
            throw new BusinessException("Invalid name!");
        }

        if (area.getMaxQuantity() == null || area.getMaxQuantity() <= 0) {
            throw new BusinessException("Invalid max quantity!");
        }

        if (area.getGmd() == null || area.getGmd() <= 0) {
            throw new BusinessException("Invalid GMD!");
        }

        for (Area getArea : areaRepository.getAll()) {
            if (getArea.getName().equals(area.getName())) {
                throw new BusinessException("Existent name!");
            }
        }
    }
}
