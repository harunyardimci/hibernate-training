package com.gg.hibernate;

import com.gg.hibernate.dao.HibernateUtils;
import com.gg.hibernate.model.Foo;
import com.gg.hibernate.model.Owner;
import com.gg.hibernate.model.Pet;
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
        //Transaction transaction = session.beginTransaction();
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

        Pet pet = new Pet();

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(o1);
        session.save(o2);

        session.save(pet);

        o1.getPets().add(pet);

        transaction.commit();
        session.close();

    }
}
