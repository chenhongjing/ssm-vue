package org.example.service;

import org.example.entity.Animal;

import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.25
 */
public interface AnimalService {
    Boolean addAnimal(Animal animal);

    List<Animal> getAllAnimals(String query);

    Boolean deleteAnimal(Integer id);

    Boolean editAnimal(Integer id, Animal animal);
}
