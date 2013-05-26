package com.gg.hibernate.dao;

import java.util.Collection;

import com.gg.hibernate.model.Visit;
import com.gg.hibernate.model.Owner;
import com.gg.hibernate.model.Person;
import com.gg.hibernate.model.Pet;
import com.gg.hibernate.model.Vet;


public interface PetClinicDao {
	
	Collection<Vet> getVets();

	Collection<Owner> findOwners(String lastName);
	
	Collection<Visit> findVisits(long petId);
	
	Collection<Person> findAllPersons();

	Owner loadOwner(long id);

	Pet loadPet(long id);
	
	Vet loadVet(long id);

	void saveOwner(Owner owner);

	void saveVet(Vet vet);

	void deleteOwner(long ownerId);
}
