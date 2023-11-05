package it.finalproject_lastversion.web.servlet.auth;

import it.finalproject_lastversion.DTO.UserDTO;
import it.finalproject_lastversion.model.Role;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/public/ExecuteLoginServlet")
public class ExecuteLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        throw new UnsupportedOperationException("Invocation of doGet not allowed for this Servlet");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginInput = request.getParameter("username");
        String passwordInput = request.getParameter("password");


        if (loginInput.isBlank() || passwordInput.isBlank()) {
            request.setAttribute("errorMessage", "E' necessario riempire tutti i campi.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String destinazione = null;

        try {
            User userInstance = MyServiceFactory.getUserServiceInstance().accedi(loginInput, passwordInput);
            if (userInstance == null) {
                request.setAttribute("errorMessage", "Credenziali errate.");
                destinazione = "login.jsp";
            } else {
                request.getSession().setAttribute("userInfo", UserDTO.createUserDTOFromModel(userInstance));
                if(userInstance.getUserRole().equals(Role.ROLE_ADMIN)){
                    destinazione = "../admin/homeAdmin.jsp";
                } else if (userInstance.getUserRole().equals(Role.ROLE_MERCHANT)) {
                    //al login del merchant verifico quante transazioni sono in attesa
                    int transazioniInAttesa = MyServiceFactory.getTransactionServiceInstance().ricercaTutteLeTransazioniCreateConIdMerchant(userInstance.getId()).size();
                    request.setAttribute("transactionCreatedNumber", transazioniInAttesa);
                    destinazione = "../merchant/homeMerchant.jsp";
                } else {
                    destinazione = "../user/homeUser.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            destinazione = "login.jsp";
            request.setAttribute("errorMessage", "Attenzione! Si è verificato un errore.");
        }

        request.getRequestDispatcher(destinazione).forward(request, response);
    }
}
