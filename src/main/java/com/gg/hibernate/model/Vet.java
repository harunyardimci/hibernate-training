package com.gg.hibernate.model;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
//@PrimaryKeyJoinColumn
public class Vet extends Person {
	
	private static final long serialVersionUID = 1L;

    @ManyToMany
    @JoinTable(name = "VET_SPECIALITY", joinColumns = @JoinColumn(name="VET_ID"), inverseJoinColumns = @JoinColumn(name = "SPEC_ID"))
    @ForeignKey(name = "FK_VET_ID", inverseName = "FK_SPEC_ID" )
	private Set<Specialty> specialties = new HashSet<Specialty>();

	public Set<Specialty> getSpecialties() {
		return Collections.unmodifiableSet(specialties);
	}
	
	public void addSpecialty(Specialty specialty) {
		specialties.add(specialty);
	}
}
