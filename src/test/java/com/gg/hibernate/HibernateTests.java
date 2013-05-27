package com.gg.hibernate;

import com.gg.hibernate.dao.HibernateUtils;
import com.gg.hibernate.dao.PetClinicDaoHibernateImpl;
import com.gg.hibernate.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/26/13
 * Time: 11:30 AM
 */
public class HibernateTests {

    @Test
    public void testHibernateSetup() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Foo foo = new Foo("harun");
        //foo.setName("harun");
        //foo.setId(1L); // @GeneratedValue annotaion'i olunca gerek yok
        session.save(foo);

        transaction.commit();
        session.close();

    }

    @Test
    public void testManyToOneBidirectional() {

        Owner o1 = new Owner();
        Owner o2 = new Owner();

        o1.setFirstName("harun");
        o1.setLastName("yardimci");

        Pet pet = new Pet();

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(o1);
        session.save(o2);

        session.save(pet);

        o1.addPet(pet);

        transaction.commit();
        session.close();

    }

    @Test
    public void testSaveOwner() {
        PetClinicDaoHibernateImpl petClinicDaoHibernate = new PetClinicDaoHibernateImpl();
        Owner owner = new Owner();
        owner.setFirstName("A");
        owner.setLastName("B");

        Pet pet = new Pet();
        pet.setName("mypet");

        owner.addPet(pet);

        petClinicDaoHibernate.saveOwner(owner);

    }

    @Test
    public void testLoadOwner() {
        PetClinicDaoHibernateImpl petClinicDaoHibernate = new PetClinicDaoHibernateImpl();

        Owner owner = petClinicDaoHibernate.loadOwner(1000);
        System.out.println(owner.getFirstName());
    }

    @Test
    public void testFetching() {

        Session session = HibernateUtils.getSessionFactory().openSession();
//        Pet pet = (Pet)session.get(Pet.class, 1L);

        Pet pet = (Pet) session.createQuery("from Pet p where p.id = 7").uniqueResult();

        System.out.println("before get visits");
//        pet.getVisits().size();
//
//        Visit visit = new Visit();
//        visit.setId(1L);
//
//        pet.getVisits().contains(visit);

        session.close();

    }
}
