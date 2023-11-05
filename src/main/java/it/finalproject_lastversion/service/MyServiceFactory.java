package it.finalproject_lastversion.service;

import it.finalproject_lastversion.DAO.card.CreditCardDAO;
import it.finalproject_lastversion.DAO.card.CreditCardDAOImpl;
import it.finalproject_lastversion.DAO.transaction.TransactionDAO;
import it.finalproject_lastversion.DAO.transaction.TransactionDAOImpl;
import it.finalproject_lastversion.DAO.user.UserDAO;
import it.finalproject_lastversion.DAO.user.UserDAOImpl;
import it.finalproject_lastversion.service.card.CreditCardService;
import it.finalproject_lastversion.service.card.CreditCardServiceImpl;
import it.finalproject_lastversion.service.transaction.TransactionService;
import it.finalproject_lastversion.service.transaction.TransactionServiceImpl;
import it.finalproject_lastversion.service.user.UserService;
import it.finalproject_lastversion.service.user.UserServiceImpl;

public class MyServiceFactory {

	private static UserService USER_SERVICE_INSTANCE;
	private static UserDAO USER_DAO_INSTANCE = null;

	private static CreditCardService CARD_SERVICE_INSTANCE;

	private static CreditCardDAO CARD_DAO_INSTANCE = null;

	private static TransactionService TRANSACTION_SERVICE_INSTANCE;

	private static TransactionDAO TRANSACTION_DAO_INSTANCE = null;

	public static UserService getUserServiceInstance() {
		if (USER_SERVICE_INSTANCE == null)
			USER_SERVICE_INSTANCE = new UserServiceImpl();

		if (USER_DAO_INSTANCE == null)
			USER_DAO_INSTANCE = new UserDAOImpl();

		USER_SERVICE_INSTANCE.setUserDAO(USER_DAO_INSTANCE);
		return USER_SERVICE_INSTANCE;
	}

	public static CreditCardService getCardServiceInstance() {
		if (CARD_SERVICE_INSTANCE == null)
			CARD_SERVICE_INSTANCE = new CreditCardServiceImpl();

		if (CARD_DAO_INSTANCE == null)
			CARD_DAO_INSTANCE = new CreditCardDAOImpl();

		CARD_SERVICE_INSTANCE.setCardDAO(CARD_DAO_INSTANCE);
		return CARD_SERVICE_INSTANCE;
	}

	public static TransactionService getTransactionServiceInstance() {
		if (TRANSACTION_SERVICE_INSTANCE == null)
			TRANSACTION_SERVICE_INSTANCE = new TransactionServiceImpl();

		if (TRANSACTION_DAO_INSTANCE == null)
			TRANSACTION_DAO_INSTANCE = new TransactionDAOImpl();

		if (CARD_DAO_INSTANCE == null)
			CARD_DAO_INSTANCE = new CreditCardDAOImpl();

		TRANSACTION_SERVICE_INSTANCE.setTransactionDAO(TRANSACTION_DAO_INSTANCE, CARD_DAO_INSTANCE);
		return TRANSACTION_SERVICE_INSTANCE;
	}


}
