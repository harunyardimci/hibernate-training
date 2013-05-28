package com.gg.hibernate;

import com.gg.hibernate.dao.HibernateUtils;
import com.gg.hibernate.dao.PetClinicDaoHibernateImpl;
import com.gg.hibernate.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSessionBuilder;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/26/13
 * Time: 11:30 AM
 */
public class HibernateTests {

    private PetClinicDaoHibernateImpl petClinicDaoHibernate;

    @Before
    public void setUp() {
        petClinicDaoHibernate = new PetClinicDaoHibernateImpl();
    }


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

    @Test
    public void testDelete() {

        PetClinicDaoHibernateImpl petClinicDaoHibernate = new PetClinicDaoHibernateImpl();
        petClinicDaoHibernate.deleteOwner(1000L);
    }

    @Test
    public void testOptLocking() {

        Session session1 = HibernateUtils.getSessionFactory().openSession();
        Session session2 = HibernateUtils.getSessionFactory().openSession();

        Transaction tx1 = session1.beginTransaction();
        Transaction tx2 = session2.beginTransaction();

        Owner o1 = (Owner) session1.get(Owner.class, 7L);
        Owner o2 = (Owner) session2.get(Owner.class, 7L);

        o1.setLastName("nnnns");
        o2.setLastName("yyy");

        tx2.commit();
        System.out.println("after tx2 commit");
        tx1.commit();
    }

    @Test
    public void testHQL() {

        Session session1 = HibernateUtils.getSessionFactory().openSession();
        List<Owner> list = session1.createQuery("select distinct o from Owner o join fetch o.pets p " +
                "left join fetch p.visits " +
                "left join fetch p.imagesByName " +
                "where p.name like 'L%'").list();

        session1.close();
        System.out.println(list.get(0).getPets().size());
    }


    @Test
    public void testGetVets() {

        Collection<Vet> vets = petClinicDaoHibernate.getVets();

        for (Vet vet :vets) {
            System.out.println(vet);
        }
    }

    @Test
    public void testFindOwners() {

        Collection<Owner> owners = petClinicDaoHibernate.findOwners("YYYY");

        for (Owner owner :owners) {
            System.out.println(owner);
        }
    }

    @Test
    public void testFindVisits() {

        Collection<Visit> visits = petClinicDaoHibernate.findVisits(7L);

        for (Visit visit :visits) {
            System.out.println(visit);
        }


    }

}
