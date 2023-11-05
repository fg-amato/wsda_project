package it.finalproject_lastversion.service.card;

import it.finalproject_lastversion.DAO.card.CreditCardDAO;
import it.finalproject_lastversion.model.CreditCard;


import java.util.List;

public interface CreditCardService {
    public List<CreditCard> listAll() throws Exception;

    public CreditCard caricaSingoloElemento(String id) throws Exception;

    public void inserisciNuovo(CreditCard creditCardInstance) throws Exception;

    public CreditCard caricaPerNumeroDiCarta(String cardNumber) throws Exception;

    public CreditCard trovaPerIdTitolareCarta(String idTitolareCarta) throws Exception;
    public CreditCard caricaSingoloElementoConNumeroDiCartaEUser(String cardNumber) throws  Exception;
    // per injection
    public void setCardDAO(CreditCardDAO creditCardDAO);

    public void disabilita(String id) throws Exception;

    public  void abilita(String id) throws  Exception;
}
