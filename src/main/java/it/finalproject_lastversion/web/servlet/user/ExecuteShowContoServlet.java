package it.finalproject_lastversion.web.servlet.user;

import it.finalproject_lastversion.DTO.CreditCardDTO;
import it.finalproject_lastversion.DTO.TransactionDTO;
import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/ExecuteShowContoServlet")
public class ExecuteShowContoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idUserParam = request.getParameter("idUser");


        if(StringUtils.isBlank(idUserParam)){
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("homeUser.jsp").forward(request, response);
            return;
        }

        try{
            CreditCard userCard = MyServiceFactory.getCardServiceInstance().trovaPerIdTitolareCarta(idUserParam);
            List<Transaction> transactionList = MyServiceFactory.getTransactionServiceInstance().trovaLeUltimeCinqueOperazioniConIdCarta(userCard.getId());
            List<TransactionDTO> toSend = new ArrayList<>(TransactionDTO.createTransactionDTOSetFromListWithoutCardHolders(transactionList));
            //mando in pagina la card senza info sullo user, poiché lo user info è lo stesso titolare della carta
            request.setAttribute("userCard", CreditCardDTO.createCardDTOFromModelWithoutTransactionsAndUser(userCard));
            request.setAttribute("transactionUserList", toSend);
            request.getRequestDispatcher("showConto.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("homeUser.jsp").forward(request, response);
        }
    }
}
