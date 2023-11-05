package it.finalproject_lastversion.DAO.transaction;

import it.finalproject_lastversion.DAO.IBaseDAO;
import it.finalproject_lastversion.model.Transaction;

import java.util.List;

public interface TransactionDAO extends IBaseDAO<Transaction> {

    //l'inserimento della tipologia mi agevola nella ricerca
    public List<Transaction> findByExample(Transaction example, String tipologia) throws Exception;

    public List<Transaction> findAllCreatedButNotConfirmedTransactionsWithSpecifiedMerchant(String idMerchant) throws Exception;

    public List<Transaction> findLastFiveConfirmedTransactionWithMerchantByCardId(String idCard)throws Exception;

    public List<Transaction> listWithMerchantAndCard() throws Exception;
}
