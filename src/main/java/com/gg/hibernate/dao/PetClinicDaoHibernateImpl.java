package com.gg.hibernate.dao;

import com.gg.hibernate.model.*;
import com.gg.hibernate.model.Pet;
import com.gg.hibernate.model.Vet;
import com.gg.hibernate.model.Owner;
import com.gg.hibernate.model.Visit;
import com.gg.hibernate.model.Person;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.Collection;
import java.util.List;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/27/13
 * Time: 3:13 PM
 */
public class PetClinicDaoHibernateImpl implements PetClinicDao {
    @Override
    public Collection<Vet> getVets() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Collection<Vet> result = session.createQuery("from Vet v left join fetch v.specialties").list();
        session.close();
        return result;
    }

    @Override
    public Collection<Owner> findOwners(String lastName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Collection<Owner> result = session.createQuery("select distinct o from Owner o " +
                "left join fetch o.pets p " +
                "left join fetch p.imagesByName " +
                "where o.lastName = :lastname").setParameter("lastname", lastName).list();
        session.close();
        return result;
    }

    @Override
    public Collection<Visit> findVisits(long petId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Visit.class).
                createAlias("pet", "p").
                createAlias("p.type", "t", JoinType.LEFT_OUTER_JOIN).
                createAlias("p.imagesByName", "i", JoinType.LEFT_OUTER_JOIN).
//                createAlias("p.owner", "o").
//                createAlias("p.pets", "p2").
                setFetchMode("p.type", FetchMode.JOIN).
                add(Restrictions.eq("p.id", petId));

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        session.close();
        return list;
    }

    @Override
    public Collection<Person> findAllPersons() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Collection<Person> result = session.createQuery("from Person").list();
        session.close();
        return result;
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
//        Owner o = new Owner();
//        o.setId(ownerId);
//        session.delete(o);

        transaction.commit();
        session.close();
    }
}
