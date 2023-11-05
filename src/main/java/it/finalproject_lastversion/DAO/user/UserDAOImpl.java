package it.finalproject_lastversion.DAO.user;

import java.util.List;
import java.util.Optional;

import it.finalproject_lastversion.model.Role;
import it.finalproject_lastversion.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    @Override
    public List<User> list() throws Exception {
        // ritorna una list di tutti gli user
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Optional<User> findOne(String id) throws Exception {
        User result = entityManager.find(User.class, id);
        return result != null ? Optional.of(result) : Optional.empty();
    }

    @Override
    public void update(User userInstance) throws Exception {
        if (userInstance == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.merge(userInstance);
    }

    @Override
    public void insert(User userInstance) throws Exception {
        if (userInstance == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.persist(userInstance);
    }

    @Override
    public void delete(User userInstance) throws Exception {
        if (userInstance == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.remove(entityManager.merge(userInstance));
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) throws Exception {
        // usato al login
        TypedQuery<User> query = entityManager.createQuery(
                "select distinct u FROM User as u  where u.username = :username and u.password=:password and u.enabled=true",
                User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<User> findByUsernameAndRoleIsClassicUserWithoutCard(String username) throws Exception {
        TypedQuery<User> query = entityManager.createQuery(
                "select distinct u FROM User as u  where u.username = :username and u.enabled=true and u.userRole= :role and u.id not in (select cc.cardHolder.id from CreditCard  as cc)",
                User.class);
        query.setParameter("username", username);
        query.setParameter("role", Role.ROLE_CLASSIC_USER);
        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<User> findByUsernameAndRoleIsMerchantUser(String username) throws Exception {
        TypedQuery<User> query = entityManager.createQuery(
                "select distinct u FROM User as u  where u.username = :username and u.userRole=:role",
                User.class);
        query.setParameter("username", username);
        query.setParameter("role", Role.ROLE_MERCHANT);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<User> findAllMerchant() throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select u FROM User as u where userRole = :role",
                User.class);
        query.setParameter("role", Role.ROLE_MERCHANT);
        return query.getResultList();
    }

}
