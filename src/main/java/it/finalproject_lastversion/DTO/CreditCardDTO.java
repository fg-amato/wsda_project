package it.finalproject_lastversion.DTO;

import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.User;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/* l'obiettivo di questa classe è il trasferimento in pagina di una carta
quando questa viene presa dal DB ed ha associate con essa le info dell'utente
per evitare che si porti dietro anche la password dell'utente
 */
public class CreditCardDTO {

    private String id;

    private String cardNumber;

    private double balance;

    private UserDTO cardHolderDTO;

    private boolean enabled;

    private Set<TransactionDTO> transactionsDTO = new HashSet<>();

    public CreditCardDTO() {
        super();
    }


    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserDTO getCardHolderDTO() {
        return cardHolderDTO;
    }

    public void setCardHolderDTO(UserDTO cardHolder) {
        this.cardHolderDTO = cardHolder;
    }

    public Set<TransactionDTO> getTransactionsDTO() {
        return transactionsDTO;
    }

    public void setTransactionsDTO(Set<TransactionDTO> transactionsDTO) {
        this.transactionsDTO = transactionsDTO;
    }


    public static CreditCardDTO createCardDTOFromModelWithoutTransactionsAndUser(CreditCard creditCard){
        CreditCardDTO cardToReturn = new CreditCardDTO();

        cardToReturn.setId(creditCard.getId());
        cardToReturn.setBalance(creditCard.getBalance());
        cardToReturn.setCardNumber(creditCard.getCardNumber());
        cardToReturn.setEnabled(creditCard.isEnabled());

        return cardToReturn;
    }

    public static CreditCardDTO createCardDTOFromModelWithoutTransactions(CreditCard creditCard){
        CreditCardDTO cardToReturn = new CreditCardDTO();

        cardToReturn.setId(creditCard.getId());
        cardToReturn.setBalance(creditCard.getBalance());
        cardToReturn.setCardNumber(creditCard.getCardNumber());
        cardToReturn.setEnabled(creditCard.isEnabled());
        if (creditCard.getCardHolder()!= null){
            cardToReturn.setCardHolderDTO(UserDTO.createUserDTOFromModel(creditCard.getCardHolder()));
        }

        return cardToReturn;
    }

    public static CreditCardDTO createCardDTOFromModelWithoutTransactionsAndCardHolder(CreditCard creditCard){
        CreditCardDTO cardToReturn = new CreditCardDTO();

        cardToReturn.setId(creditCard.getId());
        cardToReturn.setBalance(creditCard.getBalance());
        cardToReturn.setCardNumber(creditCard.getCardNumber());
        cardToReturn.setEnabled(creditCard.isEnabled());

        return cardToReturn;
    }
    public static CreditCardDTO createCardDTOFromModelWithTransactions(CreditCard creditCard){
        CreditCardDTO cardToReturn = new CreditCardDTO();

        cardToReturn.setId(creditCard.getId());
        cardToReturn.setBalance(creditCard.getBalance());
        cardToReturn.setCardNumber(creditCard.getCardNumber());
        cardToReturn.setEnabled(creditCard.isEnabled());
        if (creditCard.getCardHolder()!= null){
            cardToReturn.setCardHolderDTO(UserDTO.createUserDTOFromModel(creditCard.getCardHolder()));
        }
        cardToReturn.setTransactionsDTO(TransactionDTO.createTransactionDTOSetFromList(new ArrayList<>(creditCard.getTransactions())));
        return cardToReturn;
    }

}
