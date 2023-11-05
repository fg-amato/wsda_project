package it.finalproject_lastversion.web.servlet.merchant;

import it.finalproject_lastversion.DTO.TransactionDTO;
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

@WebServlet("/merchant/ExecuteListTransactionCreatedServlet")
public class ExecuteListTransactionCreatedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idMerchantParam = request.getParameter("idMerchant");

        if(StringUtils.isBlank(idMerchantParam)){
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
            return;
        }

        try{
            List<Transaction> transactionList = MyServiceFactory.getTransactionServiceInstance().ricercaTutteLeTransazioniCreateConIdMerchant(idMerchantParam);
            List<TransactionDTO> toSend = new ArrayList<>(TransactionDTO.createTransactionDTOSetFromListWithoutCardHolders(transactionList));
            request.setAttribute("transactionCreatedList", toSend);
            request.getRequestDispatcher("listTransactions.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
        }
    }
}
