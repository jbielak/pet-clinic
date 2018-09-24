package com.jbielak.petclinic.services;

import com.jbielak.petclinic.model.Owner;

public interface OwnerService  extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
