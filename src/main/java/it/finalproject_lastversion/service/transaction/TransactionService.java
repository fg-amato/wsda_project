package it.finalproject_lastversion.service.transaction;

import it.finalproject_lastversion.DAO.card.CreditCardDAO;
import it.finalproject_lastversion.DAO.transaction.TransactionDAO;
import it.finalproject_lastversion.model.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> listAllConCartaENegoziante() throws Exception;
    public Transaction caricaSingoloElemento(String id) throws Exception;
    public void inserisciNuovo(Transaction transactionInstance) throws Exception;

    public List<Transaction> ricercaTutteLeTransazioniCreateConIdMerchant(String idMerchant) throws Exception;
    public void confermaTransazione(String id) throws Exception;
    public void negaTransazione(String id) throws Exception;
    public List<Transaction> findByExample(Transaction transactionInstance, String tipologia) throws Exception;

    public List<Transaction> trovaLeUltimeCinqueOperazioniConIdCarta(String idCard) throws Exception;
    // per injection
    public void setTransactionDAO(TransactionDAO transactionDAO, CreditCardDAO creditCardDAO);
}
