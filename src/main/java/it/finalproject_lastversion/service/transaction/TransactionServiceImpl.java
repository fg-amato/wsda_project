package it.finalproject_lastversion.service.transaction;

import it.finalproject_lastversion.DAO.card.CreditCardDAO;
import it.finalproject_lastversion.DAO.transaction.TransactionDAO;
import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.TransactionState;
import it.finalproject_lastversion.utils.LocalEntityManagerFactoryListener;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {

    private TransactionDAO transactionDAO;

    //lo uso quando aggiorno effettivamente la transazione da stato CREATED a CONFIRMED
    private CreditCardDAO creditCardDAO;

    @Override
    public List<Transaction> listAllConCartaENegoziante() throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            transactionDAO.setEntityManager(entityManager);
            return transactionDAO.listWithMerchantAndCard();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public Transaction caricaSingoloElemento(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            transactionDAO.setEntityManager(entityManager);
            return transactionDAO.findOne(id).get();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void inserisciNuovo(Transaction transactionInstance) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // uso l'injection per il dao
            transactionDAO.setEntityManager(entityManager);
            transactionDAO.insert(transactionInstance);

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
    public List<Transaction> ricercaTutteLeTransazioniCreateConIdMerchant(String idMerchant) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // uso l'injection per il dao
            transactionDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            return transactionDAO.findAllCreatedButNotConfirmedTransactionsWithSpecifiedMerchant(idMerchant);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void confermaTransazione(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            transactionDAO.setEntityManager(entityManager);
            creditCardDAO.setEntityManager(entityManager);

            Optional<Transaction> result = transactionDAO.findOne(id);
            Transaction daConfermare = result.isPresent() ? result.get() : null;

            if (daConfermare != null) {
                daConfermare.setTransactionState(TransactionState.STATE_CONFIRMED);
                daConfermare.setDateTransaction(new Date());
                transactionDAO.update(daConfermare);

                //aggiornamento saldo carta eseguito dopo aver verificato l'esistenza della transazione
                CreditCard cardTransaction = creditCardDAO.findOne(daConfermare.getCard().getId()).get();

                Double newCardBalance = cardTransaction.getBalance() + daConfermare.getAmount();

                cardTransaction.setBalance(newCardBalance);
                creditCardDAO.update(cardTransaction);
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
    public void negaTransazione(String id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            transactionDAO.setEntityManager(entityManager);
            Optional<Transaction> result = transactionDAO.findOne(id);
            Transaction daCancellare = result.isPresent() ? result.get() : null;

            transactionDAO.delete(daCancellare);

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
    public List<Transaction> findByExample(Transaction transactionInstance, String tipologia) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // uso l'injection per il dao
            transactionDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            return transactionDAO.findByExample(transactionInstance, tipologia);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public List<Transaction> trovaLeUltimeCinqueOperazioniConIdCarta(String idCard) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // uso l'injection per il dao
            transactionDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            return transactionDAO.findLastFiveConfirmedTransactionWithMerchantByCardId(idCard);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void setTransactionDAO(TransactionDAO transactionDAO, CreditCardDAO creditCardDAO) {
        this.transactionDAO = transactionDAO;
        this.creditCardDAO = creditCardDAO;
    }
}
