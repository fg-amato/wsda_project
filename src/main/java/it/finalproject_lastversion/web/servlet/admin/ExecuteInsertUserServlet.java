package it.finalproject_lastversion.web.servlet.admin;

import java.io.IOException;

import it.finalproject_lastversion.model.Role;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.service.MyServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/admin/ExecuteInsertUserServlet")
public class ExecuteInsertUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if (StringUtils.isAnyBlank(username, password, role)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("insertUser.jsp").forward(request, response);
			return;
		}
		try {
			User tmp = new User(username, password, Role.valueOf(role));
			MyServiceFactory.getUserServiceInstance().inserisciNuovo(tmp);
			request.setAttribute("successMessage", "Aggiunto utente: "+ tmp.getUsername() + " con ruolo: "+tmp.getUserRole().toString());
			request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Qualcosa è andato storto");
			request.getRequestDispatcher("insertUser.jsp").forward(request, response);
			return;
		}
	}

}
