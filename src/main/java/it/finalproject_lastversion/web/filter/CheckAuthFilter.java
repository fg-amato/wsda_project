package it.finalproject_lastversion.web.filter;

import it.finalproject_lastversion.DTO.UserDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "CheckAuthFilter", urlPatterns = {"/*"})
public class CheckAuthFilter implements Filter {

    //categorizzo tutte le url
    private static final String HOME_PATH = "";
    private static final String[] EXCLUDED_URLS = {"/public/", "/assets/", "/js/"};

    private static final String[] AUTHENTICATED_URLS = {"/shared/"};
    private static final String[] USER_URLS = {"/user/"};

    private static final String[] MERCHANT_URLS = {"/merchant/"};
    private static final String[] ADMIN_URLS = {"/admin/"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // prendo il path della request che sta passando in questo momento
        String pathAttuale = httpRequest.getServletPath();

        // se è nella white list procedo
        if (isPathInWhiteList(pathAttuale)) {
            chain.doFilter(request, response);
            return;
        }

        // se non lo e' bisogna controllare sia sessione che percorsi protetti

        UserDTO utenteInSession = (UserDTO) httpRequest.getSession().getAttribute("userInfo");
        // intanto verifico se utente in sessione
        if (utenteInSession == null) {
            httpRequest.setAttribute("errorMessage", "Effettua il login prima");
            httpResponse.sendRedirect(httpRequest.getContextPath());
            return;
        }

        /*
        se lo user è in session può accedere al path shared
        se lo user è admin può accedere ai path solo di admin - secondo if
        se lo user è merchant può accedere ai path solo di merchant - terzo if
        se lo user è classic user può accedere ai path solo di classic user - quarto if
        se non è in nessuna di queste casistiche rimando alla welcome page con un messaggio di errore
        */
        if (isPathAuthenticated(pathAttuale)) {
            chain.doFilter(request, response);
            return;
        } else if (isPathForOnlyAdministrators(pathAttuale) && utenteInSession.isAdmin()) {
            chain.doFilter(request, response);
            return;
        } else if (isPathForOnlyMerchants(pathAttuale) && utenteInSession.isMerchant()) {
            chain.doFilter(request, response);
            return;
        } else if (isPathForOnlyUsers(pathAttuale) && utenteInSession.isClassicUser()) {
            chain.doFilter(request, response);
            return;
        }
        httpRequest.setAttribute("errorMessage", "Non si è autorizzati alla navigazione richiesta");
        httpRequest.getRequestDispatcher("/public/index.jsp").forward(httpRequest, httpResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }


    private boolean isPathInWhiteList(String requestPath) {

        if (requestPath.equals(HOME_PATH))
            return true;

        for (String urlPatternItem : EXCLUDED_URLS) {
            if (requestPath.contains(urlPatternItem)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathForOnlyAdministrators(String requestPath) {
        for (String urlPatternItem : ADMIN_URLS) {
            if (requestPath.contains(urlPatternItem)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathForOnlyMerchants(String requestPath) {
        for (String urlPatternItem : MERCHANT_URLS) {
            if (requestPath.contains(urlPatternItem)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathForOnlyUsers(String requestPath) {
        for (String urlPatternItem : USER_URLS) {
            if (requestPath.contains(urlPatternItem)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathAuthenticated(String requestPath) {
        for (String urlPatternItem : AUTHENTICATED_URLS) {
            if (requestPath.contains(urlPatternItem)) {
                return true;
            }
        }
        return false;
    }
}
