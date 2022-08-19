package com.jetbovapi.domain.animal;

import com.jetbovapi.domain.animal.model.Animal;
import com.jetbovapi.domain.db.Connect;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {
    private Connect connect;
    public AnimalRepository(Connect connect) {
        this.connect = connect;
    }
    public void save(Animal a) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into animals (earring, weigth) values (?, ?)");
            statement.setString(1, a.getEarring());
            statement.setDouble(2, a.getWeight());
            statement.executeUpdate();
        }
    }

    public List<Animal> getAll() throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from animals");
            ResultSet getAnimals = statement.executeQuery();

            List<Animal> animalsList = new ArrayList<>();
            while (getAnimals.next()) {
                animalsList.add(new Animal(getAnimals.getString("earring"), getAnimals.getDouble("weigth")));
            }
            return animalsList;
        }
    }

    public Animal getByEarring(String earring) throws Exception {
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from animals where earring = ?");
            statement.setString(1, earring);
            ResultSet selectedAnimal = statement.executeQuery();

            if (selectedAnimal.next()) {
                return new Animal(selectedAnimal.getString("earring"), selectedAnimal.getDouble("weigth"));
            }
            return null;
        }
    }

    public List<Animal> getAllSpecified(List<String> animals) throws Exception {
        try (Connection connection = connect.getConnection()) {
            List<Animal> animalsList = new ArrayList<>();
            for (String earring : animals) {
                PreparedStatement statement = connection.prepareStatement("select * from animals where earring = ?");
                statement.setString(1, earring);
                ResultSet getAnimal = statement.executeQuery();

                if (getAnimal.next()) {
                    animalsList.add(new Animal(getAnimal.getString("earring"), getAnimal.getDouble("weigth")));
                }
            }
            return animalsList;
        }
    }
}
