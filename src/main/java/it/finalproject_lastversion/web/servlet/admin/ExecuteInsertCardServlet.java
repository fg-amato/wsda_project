package it.finalproject_lastversion.web.servlet.admin;

import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/admin/ExecuteInsertCardServlet")
public class ExecuteInsertCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usernameParam = request.getParameter("username");

        try {
            User user =MyServiceFactory.getUserServiceInstance().trovaPerUsernameClassicUserSenzaCard(usernameParam);
            if (user == null){
                request.setAttribute("errorMessage", "Utente non valido.");
                request.getRequestDispatcher("insertCard.jsp").forward(request, response);
                return;
            }
            //generatore di numeri casuali inizializzando con un seed
            Random random = new Random(System.currentTimeMillis());
            //genero uno stream
            IntStream numberSequence = random.ints(16, 0,9);
            //rendo lo stream una stringa
            String cardNumber = numberSequence.mapToObj(Integer::toString).collect(Collectors.joining(""));
            //istanzio una credit card
            CreditCard toInsert = new CreditCard(cardNumber, 0, user, true);
            //inserisco con il service
            MyServiceFactory.getCardServiceInstance().inserisciNuovo(toInsert);

            request.setAttribute("successMessage", "Aggiunta carta all'utente: "+user.getUsername());
            request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("insertCard.jsp").forward(request, response);
            return;
        }
    }
}
