package com.gg.hibernate;

import com.gg.hibernate.dao.HibernateUtils;
import com.gg.hibernate.model.Foo;
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

        Foo foo = new Foo();
        foo.setName("harun");
        //foo.setId(1L); // @GeneratedValue annotaioni olunca gerek yok
        session.save(foo);

        transaction.commit();
        session.close();

    }
}
