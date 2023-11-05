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

@WebServlet("/admin/ExecuteChangeStateMerchantServlet")
public class ExecuteChangeStateMerchantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idMerchantParam = request.getParameter("idMerchant");

        if (idMerchantParam.isBlank()) {
            request.setAttribute("errorMessage", "Impossibile cambiare stato al merchant.");
            request.getRequestDispatcher("listMerchant.jsp").forward(request, response);
            return;
        }

        try {

            User userInstance = MyServiceFactory.getUserServiceInstance().caricaSingoloElemento(idMerchantParam);
            if (userInstance == null) {
                request.setAttribute("errorMessage", "Non è stata trovata alcuno user.");
                request.getRequestDispatcher("listMerchant.jsp").forward(request, response);
                return;
            }

            if (userInstance.isEnabled()) {
                MyServiceFactory.getUserServiceInstance().disabilita(idMerchantParam);
                request.setAttribute("successMessage", "Username: " + userInstance.getUsername() + " disabilitato correttamente.");
                request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
            } else {
                MyServiceFactory.getUserServiceInstance().abilita(idMerchantParam);
                request.setAttribute("successMessage", "Username: " + userInstance.getUsername() + " abilitato correttamente.");
                request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("").forward(request, response);
        }
    }
}

