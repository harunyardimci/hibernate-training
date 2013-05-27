package com.gg.hibernate.dao;

import com.gg.hibernate.model.*;
import com.gg.hibernate.model.Pet;
import com.gg.hibernate.model.Vet;
import com.gg.hibernate.model.Owner;
import com.gg.hibernate.model.Visit;
import com.gg.hibernate.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/27/13
 * Time: 3:13 PM
 */
public class PetClinicDaoHibernateImpl implements PetClinicDao {
    @Override
    public Collection<Vet> getVets() {
        return null;
    }

    @Override
    public Collection<Owner> findOwners(String lastName) {
        return null;
    }

    @Override
    public Collection<Visit> findVisits(long petId) {
        return null;
    }

    @Override
    public Collection<Person> findAllPersons() {
        return null;
    }

    @Override
    public Owner loadOwner(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Owner o = (Owner) session.load(Owner.class, id);
        //Hibernate.initialize(o);
        o.getId();
        session.close();
        return o;
    }

    @Override
    public Pet loadPet(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Pet pet = (Pet) session.get(Pet.class, id);
        session.close();

        return pet;
    }

    @Override
    public Vet loadVet(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Vet vet = (Vet)session.createQuery("from Vet v where v.id = ?").setParameter(0, id).uniqueResult();
        session.close();
        return vet;
    }

    @Override
    public void saveOwner(Owner owner) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        session.close();
    }

    @Override
    public void saveVet(Vet vet) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(vet);
        transaction.commit();
        session.close();
    }


    @Override
    public void deleteOwner(long ownerId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Owner.class, ownerId));
        transaction.commit();
        session.close();
    }
}
