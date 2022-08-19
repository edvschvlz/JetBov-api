package com.jetbovapi.domain.area;

import com.jetbovapi.domain.area.model.Area;
import com.jetbovapi.domain.db.Connect;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaRepository {
    private Connect connect;
    public AreaRepository(Connect connect) {
        this.connect = connect;
    }

    public void save(Area a) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into areas (name, maxQuantity, gmd) values (?, ?, ?)");
            statement.setString(1, a.getName());
            statement.setInt(2, a.getMaxQuantity());
            statement.setDouble(3, a.getGmd());
            statement.executeUpdate();
        }
    }

    public List<Area> getAll() throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from areas");
            ResultSet getAreas = statement.executeQuery();

            List<Area> areaList = new ArrayList<>();
            while (getAreas.next()) {
                areaList.add(new Area(getAreas.getString("name"), getAreas.getInt("maxQuantity"), getAreas.getDouble("gmd")));
            }
            return areaList;
        }
    }

    public Area getByName(String name) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from areas where name = ?");
            statement.setString(1, name);
            ResultSet selectedArea = statement.executeQuery();

            if (selectedArea.next()) {
                return new Area(selectedArea.getString("name"), selectedArea.getInt("maxQuantity"), selectedArea.getDouble("gmd"));
            }
            return null;
        }
    }
}
