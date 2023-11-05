package it.finalproject_lastversion.web.servlet.merchant;

import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebServlet("/merchant/ExecuteChangeStateTransactionServlet")
public class ExecuteChangeStateTransactionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTransazioneParam = request.getParameter("idTransazione");
        String tipoOperazioneParam = request.getParameter("Operation");

        if (StringUtils.isAnyEmpty(idTransazioneParam,tipoOperazioneParam)) {
            request.setAttribute("errorMessage", "Impossibile cambiare stato al merchant.");
            request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
            return;
        }

        try {

            Transaction transactionInstance = MyServiceFactory.getTransactionServiceInstance().caricaSingoloElemento(idTransazioneParam);
            if (transactionInstance == null) {
                request.setAttribute("errorMessage", "Non è stata trovata alcuna transazione.");
                request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
                return;
            }
            if(tipoOperazioneParam.equalsIgnoreCase("confirm")){
                MyServiceFactory.getTransactionServiceInstance().confermaTransazione(idTransazioneParam);
                request.setAttribute("successMessage", "Transazione con id: "+ transactionInstance.getId()+" confermata.");
                request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
            } else{
                MyServiceFactory.getTransactionServiceInstance().negaTransazione(idTransazioneParam);
                request.setAttribute("successMessage", "Transazione con id: "+ transactionInstance.getId()+" stornata.");
                request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("homeMerchant.jsp").forward(request, response);
        }
    }


}
