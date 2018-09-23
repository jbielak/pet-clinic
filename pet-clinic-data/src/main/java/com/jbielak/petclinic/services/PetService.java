package com.jbielak.petclinic.services;

import com.jbielak.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findbyId(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
