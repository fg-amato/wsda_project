package it.finalproject_lastversion.web.servlet.admin;

import it.finalproject_lastversion.service.MyServiceFactory;
import it.finalproject_lastversion.utils.LocalEntityManagerFactoryListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/ExecuteListMerchantServlet")
public class ExecuteListMerchantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            request.setAttribute("merchant_list_attribute",
                    MyServiceFactory.getUserServiceInstance().trovaTuttiIMerchant());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("listMerchant.jsp").forward(request, response);
    }
}
