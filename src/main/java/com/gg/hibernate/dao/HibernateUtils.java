package com.gg.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/26/13
 * Time: 11:24 AM
 */
public class HibernateUtils {


    private static SessionFactory sessionFactory;

    static {
//        AnnotationConfiguration cfg = new AnnotationConfiguration();
        Configuration cfg = new Configuration();
        cfg.setInterceptor(new AuditLogInterceptor());
        cfg.configure();
//        sessionFactory = cfg.buildSessionFactory();
//        sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry());
        sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().buildServiceRegistry());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
