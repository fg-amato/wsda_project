package it.finalproject_lastversion.DTO;

import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.TransactionState;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransactionDTO {

    private String id;

    private Double amount;

    TransactionState transactionState;

    // the one who executes the transaction

    private UserDTO merchantDTO;

    private CreditCardDTO cardDTO;

    private Date dateTransaction;

    public TransactionDTO() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public UserDTO getMerchantDTO() {
        return merchantDTO;
    }

    public void setMerchantDTO(UserDTO merchantDTO) {
        this.merchantDTO = merchantDTO;
    }

    public CreditCardDTO getCardDTO() {
        return cardDTO;
    }

    public void setCardDTO(CreditCardDTO cardDTO) {
        this.cardDTO = cardDTO;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public static TransactionDTO createTransactionDTOFromModel(Transaction transaction){
        TransactionDTO toReturn = new TransactionDTO();

        toReturn.setId(transaction.getId());
        toReturn.setAmount(transaction.getAmount());
        toReturn.setCardDTO(CreditCardDTO.createCardDTOFromModelWithoutTransactions(transaction.getCard()));
        toReturn.setMerchantDTO(UserDTO.createUserDTOFromModel(transaction.getMerchant()));
        toReturn.setDateTransaction(transaction.getDateTransaction());
        toReturn.setTransactionState(transaction.getTransactionState());

        return toReturn;
    }


    public static Set<TransactionDTO> createTransactionDTOSetFromList(List<Transaction> list){
        Set<TransactionDTO> toReturn = new HashSet<>();
        for(Transaction transactionElement : list){
            toReturn.add(TransactionDTO.createTransactionDTOFromModel(transactionElement));
        }
        return toReturn;
    }


    public static TransactionDTO createTransactionDTOFromModelWithoutCardHolders(Transaction transaction){
        TransactionDTO toReturn = new TransactionDTO();

        toReturn.setId(transaction.getId());
        toReturn.setAmount(transaction.getAmount());
        toReturn.setCardDTO(CreditCardDTO.createCardDTOFromModelWithoutTransactionsAndCardHolder(transaction.getCard()));
        toReturn.setMerchantDTO(UserDTO.createUserDTOFromModel(transaction.getMerchant()));
        toReturn.setDateTransaction(transaction.getDateTransaction());
        toReturn.setTransactionState(transaction.getTransactionState());

        return toReturn;
    }
    public static Set<TransactionDTO> createTransactionDTOSetFromListWithoutCardHolders(List<Transaction> list){
        Set<TransactionDTO> toReturn = new HashSet<>();
        for(Transaction transactionElement : list){
            toReturn.add(TransactionDTO.createTransactionDTOFromModelWithoutCardHolders(transactionElement));
        }
        return toReturn;
    }

}
