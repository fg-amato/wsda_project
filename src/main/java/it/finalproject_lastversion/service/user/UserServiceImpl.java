package it.finalproject_lastversion.service.user;

import java.util.List;
import java.util.Optional;

import it.finalproject_lastversion.DAO.user.UserDAO;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.utils.LocalEntityManagerFactoryListener;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User caricaSingoloElemento(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            userDAO.setEntityManager(entityManager);
            return userDAO.findOne(id).get();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    @Transactional
    public void inserisciNuovo(User userInstance) throws Exception {
        // questo è come una connection
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // uso l'injection per il dao
            userDAO.setEntityManager(entityManager);
            userInstance.setEnabled(true);
            userDAO.insert(userInstance);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public User accedi(String username, String password) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            userDAO.setEntityManager(entityManager);
            Optional<User> result = userDAO.findByUsernameAndPassword(username, password);
            return result.isPresent() ? result.get() : null;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public User trovaPerUsernameClassicUserSenzaCard(String username) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            userDAO.setEntityManager(entityManager);
            Optional<User> result = userDAO.findByUsernameAndRoleIsClassicUserWithoutCard(username);
            return result.isPresent() ? result.get() : null;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public User trovaPerUsernameMerchantUser(String username) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            userDAO.setEntityManager(entityManager);
            Optional<User> result = userDAO.findByUsernameAndRoleIsMerchantUser(username);
            return result.isPresent() ? result.get() : null;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }


    @Override
    public List<User> trovaTuttiIMerchant() throws Exception {

        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            userDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            return userDAO.findAllMerchant();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }


    @Override
    @Transactional
    public void disabilita(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            userDAO.setEntityManager(entityManager);
            Optional<User> result = userDAO.findOne(id);
            User daDisabilitare = result.isPresent() ? result.get() : null;

            if (daDisabilitare != null) {
                daDisabilitare.setEnabled(false);
                userDAO.update(daDisabilitare);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void abilita(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            userDAO.setEntityManager(entityManager);
            Optional<User> result = userDAO.findOne(id);
            User daAbilitare = result.isPresent() ? result.get() : null;

            if (daAbilitare != null) {
                daAbilitare.setEnabled(true);
                userDAO.update(daAbilitare);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

}
