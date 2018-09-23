package com.jbielak.petclinic.services;

import com.jbielak.petclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findbyId(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
