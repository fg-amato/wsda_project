package it.finalproject_lastversion.web.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Handler;

@WebServlet("/admin/PrepareSearchTransactionReportServlet")
public class PrepareSearchTransactionServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("searchTransactionAdmin.jsp").forward(request, response);
    }
}
