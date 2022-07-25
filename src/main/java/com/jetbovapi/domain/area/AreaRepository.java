package com.jetbovapi.domain.area;

import com.jetbovapi.domain.area.model.Area;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaRepository {
    private final List<Area> areaList = new ArrayList<>();

    public void save(Area a) {
        Area area = new Area();
        area.setName(a.getName());
        area.setMaxQuantity(a.getMaxQuantity());
        area.setGmd(a.getGmd());
        areaList.add(area);
    }

    public List<Area> getAll() {
        return areaList;
    }

    public Area getByName(String name) {
        return areaList.stream()
                .filter(a -> name.equals(a.getName()))
                .findAny()
                .orElse(null);
    }
}
