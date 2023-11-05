package it.finalproject_lastversion.web.servlet;

import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/public/CheckBalanceCardServlet")
public class CheckBalanceCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber");

        if (cardNumber.isBlank() || cardNumber.length() != 16) {
            request.setAttribute("errorMessage", "Numero di carta non valido.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        try {
            CreditCard cardFromDB = MyServiceFactory.getCardServiceInstance().caricaPerNumeroDiCarta(cardNumber);
            if (cardFromDB == null){
                request.setAttribute("errorMessage", "Qualcosa è andato storto.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
            request.setAttribute("cardNumber", cardFromDB.getCardNumber());
            request.setAttribute("saldo", cardFromDB.getBalance());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
