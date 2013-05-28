package com.gg.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/26/13
 * Time: 11:24 AM
 */
public class HibernateUtils {


    private static SessionFactory sessionFactory;

    static {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.setInterceptor(new AuditLogInterceptor());
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
