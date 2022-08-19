package com.jetbovapi.domain.movement;

import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.area.model.Area;
import com.jetbovapi.domain.db.Connect;
import com.jetbovapi.domain.movement.model.Movement;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovementRepository {
    private Connect connect;
    public MovementRepository(Connect connect) {
        this.connect = connect;
    }

    public Movement save(Movement movement) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into movements (areas_name, days) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, movement.getArea().getName());
            statement.setInt(2, movement.getDays());
            statement.executeUpdate();
            ResultSet idMovementInfo = statement.getGeneratedKeys();

            if (idMovementInfo.next()) {
                movement.setId(idMovementInfo.getInt(1));
                for (Animal animal : movement.getAnimals()) {
                    statement = connection.prepareStatement("insert into movements_has_animals (movements_id, animals_earring) values (?, ?)");
                    statement.setInt(1, movement.getId());
                    statement.setString(2, animal.getEarring());
                    statement.executeUpdate();
                }
            }
            return movement;
        }
    }

    public List<Movement> getAll() throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from movements left join areas on (areas.name = movements.areas_name) left join movements_has_animals on (movements.id = movements_has_animals.movements_id) left join animals on (animals.earring = movements_has_animals.animals_earring)");
            ResultSet movements = statement.executeQuery();

            HashMap<Integer, Movement> map = new HashMap<>();
            while (movements.next()) {
                if (map.containsKey(movements.getInt("movements_id"))) {
                    map.get(movements.getInt("movements_id")).getAnimals().add(new Animal(movements.getString("earring"), movements.getDouble("weigth")));
                } else {
                    List<Animal> animalList = new ArrayList<>();
                    animalList.add(new Animal(movements.getString("earring"), movements.getDouble("weigth")));
                    map.put(movements.getInt("movements_id"), new Movement(movements.getInt("movements_id"), animalList, new Area(movements.getString("name"), movements.getInt("maxQuantity"), movements.getDouble("gmd")), movements.getInt("days")));
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    public Movement getByAnimal(Animal animal) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from movements_has_animals inner join movements on (movements.id = movements_has_animals.movements_id) inner join areas on (areas.name = movements.areas_name) where animals_earring = ?");
            statement.setString(1, animal.getEarring());
            ResultSet movementInfo = statement.executeQuery();

            if (movementInfo.next()) {
                statement = connection.prepareStatement("select * from movements_has_animals inner join animals on (movements_has_animals.animals_earring = animals.earring) where movements_id = ?");
                statement.setInt(1, movementInfo.getInt("id"));
                ResultSet movementAnimalsInfo = statement.executeQuery();

                List<Animal> animalList = new ArrayList<>();
                while (movementAnimalsInfo.next()) {
                    animalList.add(new Animal(movementAnimalsInfo.getString("earring"), movementAnimalsInfo.getDouble("weigth")));
                }
                return new Movement(movementInfo.getInt("movements_id"), animalList, new Area(movementInfo.getString("name"), movementInfo.getInt("maxQuantity"), movementInfo.getDouble("gmd")), movementInfo.getInt("days"));
            }
        }
        return null;
    }

    public List<Movement> getWithSameArea(Area area) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from movements inner join areas on (areas.name = movements.areas_name) inner join movements_has_animals on (movements.id = movements_has_animals.movements_id) inner join animals on (animals.earring = movements_has_animals.animals_earring) where areas_name = ?");
            statement.setString(1, area.getName());
            ResultSet movements = statement.executeQuery();

            HashMap<Integer, Movement> map = new HashMap<>();
            while (movements.next()) {
                if (map.containsKey(movements.getInt("movements_id"))) {
                    map.get(movements.getInt("movements_id")).getAnimals().add(new Animal(movements.getString("earring"), movements.getDouble("weigth")));
                } else {
                    List<Animal> animalList = new ArrayList<>();
                    animalList.add(new Animal(movements.getString("earring"), movements.getDouble("weigth")));
                    map.put(movements.getInt("movements_id"), new Movement(movements.getInt("movements_id"), animalList, new Area(movements.getString("name"), movements.getInt("maxQuantity"), movements.getDouble("gmd")), movements.getInt("days")));
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    public void removeAnimal(Movement movement, Animal animal) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from movements_has_animals where animals_earring = ?");
            statement.setString(1, animal.getEarring());
            statement.executeUpdate();

            statement = connection.prepareStatement("update animals set weigth = ? where earring = ?");
            statement.setDouble(1, animal.getWeight() + (movement.getArea().getGmd() * movement.getDays()));
            statement.setString(2, animal.getEarring());
            statement.executeUpdate();

            statement = connection.prepareStatement("select * from movements_has_animals where movements_id = ?");
            statement.setInt(1, movement.getId());
            ResultSet removeIfIsEmpty = statement.executeQuery();

            if (!removeIfIsEmpty.next()) {
                statement = connection.prepareStatement("delete from movements where id = ?");
                statement.setInt(1, movement.getId());
                statement.executeUpdate();
            }
        }
    }
}
