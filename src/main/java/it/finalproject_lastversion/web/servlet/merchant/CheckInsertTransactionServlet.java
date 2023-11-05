package it.finalproject_lastversion.web.servlet.merchant;

import it.finalproject_lastversion.DTO.TransactionDTO;
import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.TransactionState;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;


@WebServlet("/merchant/CheckInsertTransactionServlet")
public class CheckInsertTransactionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardNumberParam = request.getParameter("cardNumber");
        String tipologiaParam = request.getParameter("tipologia"); //è semplicemente un + o -
        String importoParam = request.getParameter("importo");
        String merchantIdParam = request.getParameter("merchantId");

        if (StringUtils.isAnyEmpty(cardNumberParam, tipologiaParam, importoParam, merchantIdParam)) {
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
            return;
        }
        try {
            //carico la carta associata al numero e lo user
            CreditCard cardToAddTransaction = MyServiceFactory.getCardServiceInstance().caricaSingoloElementoConNumeroDiCartaEUser(cardNumberParam);
            //prima di effettuare qualsiasi operazione verifico che la carta sia enabled, altrimenti storno l'operazione
            if(!cardToAddTransaction.isEnabled()){
                request.setAttribute("errorMessage", "Carta non abilitata. Impossibile eseguire la transazione.");
                request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
                return;
            }

            //recupero il merchant associato alla transaction
            User merchantOperating = MyServiceFactory.getUserServiceInstance().caricaSingoloElemento(merchantIdParam);
            //costruisco l'importo dell'operazione
            String importoString = tipologiaParam + importoParam; //concateno + e - con l'importo
            if (!NumberUtils.isCreatable(importoString) || merchantOperating == null) {
                request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
                request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
                return;
            }
            //converto in numero con due sole cifre decimali
            BigDecimal bd = new BigDecimal(Double.parseDouble(importoString)).setScale(2, RoundingMode.HALF_UP);
            Double importoNumerico = bd.doubleValue();

            // se l'importo è negativo verifico che il saldo disponibile nella carta sia sufficiente
            if (importoNumerico < 0 && Math.abs(importoNumerico) > cardToAddTransaction.getBalance()) {
                request.setAttribute("errorMessage", "Operazione stornata, saldo non sufficiente.");
                request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
                return;
            }
            Transaction toAdd = new Transaction(importoNumerico, merchantOperating, cardToAddTransaction);
            //imposto lo stato a created perché poi manderò in una pagina di recap in cui il merchant deve confermare o annullare la transazione
            //non aggiorno il saldo della carta
            toAdd.setTransactionState(TransactionState.STATE_CREATED);
            MyServiceFactory.getTransactionServiceInstance().inserisciNuovo(toAdd);
            request.setAttribute("transactionToCheck", TransactionDTO.createTransactionDTOFromModel(toAdd));
            request.getRequestDispatcher("checkTransaction.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Qualcosa è andato storto");
            request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
            return;
        }
    }
}
