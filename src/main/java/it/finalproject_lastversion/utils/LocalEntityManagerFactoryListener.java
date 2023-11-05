package it.finalproject_lastversion.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

    private static EntityManagerFactory entityManagerFactory;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("wsda-unit");
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return entityManagerFactory.createEntityManager();

    }

    public static void closeEntityManager(EntityManager em) {
        if (em != null) {
            try {
                if (em.isOpen()) {
                    em.close();
                }
            } catch (PersistenceException ex) {
                System.err.println("Could not close JPA EntityManager" + ex);
            } catch (Throwable ex) {
                System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
            }
        }
    }



}
