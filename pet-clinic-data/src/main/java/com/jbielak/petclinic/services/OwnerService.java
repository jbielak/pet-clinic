package com.jbielak.petclinic.services;

import com.jbielak.petclinic.model.Owner;

import java.util.List;

public interface OwnerService  extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
