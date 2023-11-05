package it.finalproject_lastversion.DAO.card;

import java.util.Optional;

import it.finalproject_lastversion.DAO.IBaseDAO;
import it.finalproject_lastversion.model.CreditCard;

public interface CreditCardDAO extends IBaseDAO<CreditCard> {

	public Optional<CreditCard> findByCardHolderId (String idCardHolder) throws Exception;
	//eager
	public Optional<CreditCard> findByCardNumberWithUser(String cardNumber) throws Exception;

	public Optional<CreditCard> findByCardNumberLazy(String cardNumber) throws Exception;


}
