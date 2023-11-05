package it.finalproject_lastversion.DAO.card;

import java.util.List;
import java.util.Optional;

import it.finalproject_lastversion.model.CreditCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CreditCardDAOImpl implements CreditCardDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public List<CreditCard> list() throws Exception {
		// list di tutte le carte
		return entityManager.createQuery("from CreditCard", CreditCard.class).getResultList();
	}

	@Override
	public Optional<CreditCard> findOne(String id) throws Exception {
		CreditCard result = entityManager.find(CreditCard.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(CreditCard cardInstance) throws Exception {
		if (cardInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(cardInstance);
	}

	@Override
	public void insert(CreditCard cardInstance) throws Exception {
		if (cardInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(cardInstance);
	}

	@Override
	public void delete(CreditCard cardInstance) throws Exception {
		// non è prevista la rimozione di una carta di credito

	}

	@Override
	public Optional<CreditCard> findByCardHolderId(String idCardHolder) throws Exception {
		TypedQuery<CreditCard> query = entityManager
				.createQuery("select distinct c FROM CreditCard as c left join fetch c.cardHolder where c.cardHolder.id = :idCardHolder", CreditCard.class);
		query.setParameter("idCardHolder", idCardHolder);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<CreditCard> findByCardNumberWithUser(String cardNumber) throws Exception {
		TypedQuery<CreditCard> query = entityManager
				.createQuery("select distinct c FROM CreditCard as c left join fetch c.cardHolder where c.cardNumber = :cardNumber", CreditCard.class);
		query.setParameter("cardNumber", cardNumber);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<CreditCard> findByCardNumberLazy(String cardNumber) throws Exception {
		TypedQuery<CreditCard> query = entityManager
				.createQuery("select distinct c FROM CreditCard as c where c.cardNumber = :cardNumber", CreditCard.class);
		query.setParameter("cardNumber", cardNumber);
		return query.getResultStream().findFirst();
	}

}
