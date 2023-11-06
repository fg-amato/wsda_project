package it.finalproject_lastversion.DAO.transaction;

import it.finalproject_lastversion.model.Role;
import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.TransactionState;
import it.finalproject_lastversion.model.User;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class TransactionDAOImpl implements TransactionDAO {

    private EntityManager entityManager;

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Transaction> list() throws Exception {
        return entityManager.createQuery("from Transaction ", Transaction.class).getResultList();
    }

    @Override
    public Optional<Transaction> findOne(String id) throws Exception {
        Transaction result = entityManager.find(Transaction.class, id);
        return result != null ? Optional.of(result) : Optional.empty();
    }

    @Override
    public void update(Transaction transactionInstance) throws Exception {
        if (transactionInstance == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.merge(transactionInstance);
    }

    @Override
    public void insert(Transaction transactionInstance) throws Exception {
        if (transactionInstance == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.persist(transactionInstance);
    }

    @Override
    public void delete(Transaction transactionInstance) throws Exception {
        if (transactionInstance == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.remove(entityManager.merge(transactionInstance));
    }


    @Override
    public List<Transaction> findByExample(Transaction example, String tipologia) throws Exception {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select t from Transaction as t left join fetch t.merchant left join fetch t.card where t.transactionState = :transactionState");
        paramaterMap.put("transactionState", TransactionState.STATE_CONFIRMED);

        if (example.getMerchant()!=null) {
            whereClauses.add(" t.merchant.id  = :idMerchant ");
            paramaterMap.put("idMerchant", example.getMerchant().getId());
        }

        if (example.getAmount() != null) {
            //se tipologia è addebito il valore è - quindi voglio i valori minori
            if (tipologia.equalsIgnoreCase("-")) {
                whereClauses.add(" t.amount <= :amount ");
                paramaterMap.put("amount", example.getAmount());
            } else {
                whereClauses.add(" t.amount >= :amount ");
                paramaterMap.put("amount", example.getAmount());
            }
        }

        if (example.getDateTransaction() != null) {
            whereClauses.add(" t.dateTransaction >= :dateTransaction ");
            paramaterMap.put("dateTransaction", example.getDateTransaction());
        }

        if (example.getCard()!=null) {
            whereClauses.add(" t.card.cardNumber = :cardNumber ");
            paramaterMap.put("cardNumber", example.getCard().getCardNumber());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        queryBuilder.append(" order by t.dateTransaction desc");
        TypedQuery<Transaction> typedQuery = entityManager.createQuery(queryBuilder.toString(), Transaction.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }

    @Override
    public List<Transaction> findAllCreatedButNotConfirmedTransactionsWithSpecifiedMerchant(String idMerchant) throws Exception {
        TypedQuery<Transaction> query = entityManager.createQuery("select t FROM Transaction as t left join fetch t.card  as c left join fetch t.merchant where t.merchant.id = :idMerchant and t.transactionState=:transactionState order by t.dateTransaction desc",
                Transaction.class);
        query.setParameter("idMerchant", idMerchant);
        query.setParameter("transactionState", TransactionState.STATE_CREATED);
        return query.getResultList();
    }

    @Override
    public List<Transaction> findLastFiveConfirmedTransactionWithMerchantByCardId(String idCard) throws Exception {
        TypedQuery<Transaction> query = entityManager.createQuery("select t FROM Transaction as t left join fetch t.card as c left join fetch t.merchant where t.card.id = :idCard and t.transactionState=:transactionState order by t.dateTransaction desc limit 5",
                Transaction.class);
        query.setParameter("idCard", idCard);
        query.setParameter("transactionState", TransactionState.STATE_CONFIRMED);
        return query.getResultList();
    }

    @Override
    public List<Transaction> listWithMerchantAndCard() throws Exception {
        return entityManager.createQuery("from Transaction as t left join fetch t.card left join fetch t.merchant order by t.dateTransaction desc ", Transaction.class).getResultList();
    }
}
