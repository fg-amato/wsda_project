package it.finalproject_lastversion.service.card;


import it.finalproject_lastversion.DAO.card.CreditCardDAO;
import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.utils.LocalEntityManagerFactoryListener;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class CreditCardServiceImpl implements CreditCardService {
    private CreditCardDAO creditCardDAO;

    @Override
    public List<CreditCard> listAll() throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            creditCardDAO.setEntityManager(entityManager);
            return creditCardDAO.list();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public CreditCard caricaSingoloElemento(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            creditCardDAO.setEntityManager(entityManager);
            return creditCardDAO.findOne(id).get();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    @Transactional
    public void inserisciNuovo(CreditCard creditCardInstance) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // uso l'injection per il dao
            creditCardDAO.setEntityManager(entityManager);

            creditCardDAO.insert(creditCardInstance);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }


    //it returns the card without any info about the user
    @Override
    public CreditCard caricaPerNumeroDiCarta(String cardNumber) throws Exception {

        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            creditCardDAO.setEntityManager(entityManager);
            Optional<CreditCard> result = creditCardDAO.findByCardNumberLazy(cardNumber);
            return result.isPresent() ? result.get() : null;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public CreditCard trovaPerIdTitolareCarta(String idTitolareCarta) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            creditCardDAO.setEntityManager(entityManager);
            Optional<CreditCard> result = creditCardDAO.findByCardHolderId(idTitolareCarta);
            return result.isPresent() ? result.get() : null;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    //it returns the card with the info about the user
    @Override
    public CreditCard caricaSingoloElementoConNumeroDiCartaEUser(String cardNumber) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            creditCardDAO.setEntityManager(entityManager);
            return creditCardDAO.findByCardNumberWithUser(cardNumber).get();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void setCardDAO(CreditCardDAO creditCardDAO) {
        this.creditCardDAO = creditCardDAO;
    }

    @Override
    @Transactional
    public void disabilita(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            creditCardDAO.setEntityManager(entityManager);
            Optional<CreditCard> result = creditCardDAO.findOne(id);
            CreditCard daDisabilitare = result.isPresent() ? result.get() : null;

            if (daDisabilitare != null) {
                daDisabilitare.setEnabled(false);
                creditCardDAO.update(daDisabilitare);
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
    @Transactional
    public void abilita(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            creditCardDAO.setEntityManager(entityManager);
            Optional<CreditCard> result = creditCardDAO.findOne(id);
            CreditCard daAbilitare = result.isPresent() ? result.get() : null;

            if (daAbilitare != null) {
                daAbilitare.setEnabled(true);
                creditCardDAO.update(daAbilitare);
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
