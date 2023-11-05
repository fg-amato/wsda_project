package it.finalproject_lastversion.web.servlet.admin;

import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.service.MyServiceFactory;
import it.finalproject_lastversion.utils.LocalEntityManagerFactoryListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/ExecuteChangeStateCardServlet")
public class ExecuteChangeStateCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCartaParam = request.getParameter("idCarta");

        if (idCartaParam.isBlank()) {
            request.setAttribute("errorMessage", "Impossibile cambiare stato alla carta.");
            request.getRequestDispatcher("searchCard.jsp").forward(request, response);
            return;
        }

        try {

            CreditCard creditCardInstance = MyServiceFactory.getCardServiceInstance().caricaSingoloElemento(idCartaParam);
            if (creditCardInstance == null){
                request.setAttribute("errorMessage", "Non è stata trovata alcuna carta.");
                request.getRequestDispatcher("searchCard.jsp").forward(request, response);
                return;
            }

            if(creditCardInstance.isEnabled()){
                MyServiceFactory.getCardServiceInstance().disabilita(idCartaParam);
                request.setAttribute("successMessage", "Carta con numero "+creditCardInstance.getCardNumber()+" disabilitata correttamente.");
                request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
            } else{
                MyServiceFactory.getCardServiceInstance().abilita(idCartaParam);
                request.setAttribute("successMessage", "Carta con numero "+creditCardInstance.getCardNumber()+" abilitata correttamente.");
                request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("").forward(request, response);
        }
    }
}
