package ir.maktab.twitter.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("TwitterUnit");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
