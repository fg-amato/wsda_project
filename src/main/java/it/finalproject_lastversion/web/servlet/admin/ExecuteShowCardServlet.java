package it.finalproject_lastversion.web.servlet.admin;

import it.finalproject_lastversion.DTO.CreditCardDTO;
import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.model.Role;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebServlet ("/admin/ExecuteShowCardServlet")
public class ExecuteShowCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber");
        if (StringUtils.isBlank(cardNumber) || cardNumber.length()!= 16) {
            request.setAttribute("errorMessage", "Numero di carta non valido.");
            request.getRequestDispatcher("searchCard.jsp").forward(request, response);
            return;
        }
        try {
            CreditCard cardFoundInDB = MyServiceFactory.getCardServiceInstance().caricaSingoloElementoConNumeroDiCartaEUser(cardNumber);
            if(cardFoundInDB == null){
                request.setAttribute("errorMessage", "Non esiste alcuna carta con questo numero.");
                request.getRequestDispatcher("searchCard.jsp").forward(request, response);
                return;
            }
            CreditCardDTO cardToShow = CreditCardDTO.createCardDTOFromModelWithoutTransactions(cardFoundInDB);
            request.setAttribute("cardToShow", cardToShow);

            request.getRequestDispatcher("showCard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Qualcosa è andato storto");
            request.getRequestDispatcher("searchCard.jsp").forward(request, response);
        }
    }
}
