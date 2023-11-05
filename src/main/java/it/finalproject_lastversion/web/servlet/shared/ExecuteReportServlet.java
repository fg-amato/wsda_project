package it.finalproject_lastversion.web.servlet.shared;

import it.finalproject_lastversion.model.CreditCard;
import it.finalproject_lastversion.model.Transaction;
import it.finalproject_lastversion.model.User;
import it.finalproject_lastversion.service.MyServiceFactory;
import it.finalproject_lastversion.utils.ReportGeneratorUtil;
import it.finalproject_lastversion.utils.UtilityConversion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/shared/ExecuteReportServlet")
public class ExecuteReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userTypeParam = request.getParameter("userTypeParam");
        String idUser = request.getParameter("userId");
        if (StringUtils.isAnyBlank(userTypeParam, idUser)) {
            request.setAttribute("errorMessage", "Impossibile completare la richiesta");
            request.getRequestDispatcher("../public/index.jsp").forward(request, response);
            return;
        }

        response.setContentType("application/pdf");
        //utile per nominare il file in maniera univoca
        Date dataAttuale = new Date();
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy_MM_dd");
        String dataAttualeFormattata = parseFormat.format(dataAttuale);
        //oggetto che mi servirà per la query
        Transaction example = new Transaction();
        //lista da cui generare il pdf
        List<Transaction> transactions = new ArrayList<>();
        /*
        racchiudo tutto dentro un blocco try catch perché in base al ruolo gestisco come interrogare il db:
        1. se sei user puoi ricercare le tue transazioni (relative alla mia carta) con importo, tipologia e periodo
        2. se sei merchant puoi ricercare le tue transazioni (che hanno me come merchant) con importo, tipologia e periodo
        3. se sei admin puoi ricercare tra TUTTE le transazioni per importo, tipologia, periodo, n.ro carta e negoziante
        */

        try {

            if (userTypeParam.equals("CLASSIC_USER")) {

                CreditCard userCard = MyServiceFactory.getCardServiceInstance().trovaPerIdTitolareCarta(idUser);
                //binding parametri richiesta
                String importoParam = request.getParameter("importo");
                String tipologiaParam = request.getParameter("tipologia");
                String dateMinTransaction = request.getParameter("dateTransaction");

                example.setCard(userCard);

                //metodo di utilità che mi evita la duplicazione di blocchi di codice aventi la stessa logica
                example.setAmount(UtilityConversion.generateImport(tipologiaParam, importoParam));

                //valorizzo la data se possibile
                example.setDateTransaction(UtilityConversion.parseDateFromString(dateMinTransaction));

                //faccio la query al db che mi trova le transazioni richieste
                transactions = MyServiceFactory.getTransactionServiceInstance().findByExample(example, tipologiaParam);
            } else if (userTypeParam.equals("ADMIN")) {
                //binding parametri richiesta
                String importoParam = request.getParameter("importo");
                String tipologiaParam = request.getParameter("tipologia");
                String dateMinTransaction = request.getParameter("dateTransaction");
                String numeroCartaParam = request.getParameter("cardNumber");
                String merchantUsernameParam = request.getParameter("merchantUsername");

                //metodo di utilità che mi evita la duplicazione di blocchi di codice aventi la stessa logica
                example.setAmount(UtilityConversion.generateImport(tipologiaParam, importoParam));
                //valorizzo la data se possibile
                example.setDateTransaction(UtilityConversion.parseDateFromString(dateMinTransaction));
                //il metodo del service ritorna o un'istanza di carta o null
                example.setCard(MyServiceFactory.getCardServiceInstance().caricaPerNumeroDiCarta(numeroCartaParam));
                //il metodo del service ritorna o un'istanza di user o null
                example.setMerchant(MyServiceFactory.getUserServiceInstance().trovaPerUsernameMerchantUser(merchantUsernameParam));

                //faccio la query al db che mi trova le transazioni richieste
                transactions = MyServiceFactory.getTransactionServiceInstance().findByExample(example, tipologiaParam);
            } else {
                //nel caso del merchant, la funzionalità che differisce sta nel fatto che mentre nello user ricercavamo solo
                //transazioni aventi lo stesso numero di carta di quello user, adesso ricerchiamo transazioni aventi come merchant
                //lo user che effettua la richiesta

                User userMerchant = MyServiceFactory.getUserServiceInstance().caricaSingoloElemento(idUser);
                //binding parametri richiesta
                String importoParam = request.getParameter("importo");
                String tipologiaParam = request.getParameter("tipologia");
                String dateMinTransaction = request.getParameter("dateTransaction");

                example.setMerchant(userMerchant);

                //metodo di utilità che mi evita la duplicazione di blocchi di codice aventi la stessa logica
                example.setAmount(UtilityConversion.generateImport(tipologiaParam, importoParam));

                //valorizzo la data se possibile
                example.setDateTransaction(UtilityConversion.parseDateFromString(dateMinTransaction));

                //faccio la query al db che mi trova le transazioni richieste
                transactions = MyServiceFactory.getTransactionServiceInstance().findByExample(example, tipologiaParam);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("../public/index.jsp").forward(request, response);
        }
        /*
        codice per generare il pdf
        response.setContentType("application/pdf");
        //utile per nominare il file in maniera univoca
        Date dataAttuale = new Date();
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy_MM_dd");
        String dataAttualeFormattata = parseFormat.format(dataAttuale);
        List<Transaction> transactions = new ArrayList<>();
        try {
            transactions = MyServiceFactory.getTransactionServiceInstance().listAllConCartaENegoziante();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        response.setHeader("Content-Disposition", "attachment; filename=" + dataAttualeFormattata + "_transaction_table_" + idUser + ".pdf");

        PDDocument report = ReportGeneratorUtil.generaReport(transactions);
        if (report == null) {
            return;
        }
        report.save(response.getOutputStream());
        report.close();

    }

}

