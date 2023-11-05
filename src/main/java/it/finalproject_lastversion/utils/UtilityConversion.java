package it.finalproject_lastversion.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityConversion {
    public static Date parseDateFromString(String dataStringParam) {
        if (StringUtils.isBlank(dataStringParam))
            return null;

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dataStringParam);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Double generateImport(String tipologia, String importo) {
        //verifico che la tipologia non sia none ossia che non voglia accrediti e addebiti
        if (!tipologia.equalsIgnoreCase("none")) {
            //se tipologia diverso da none allora verifico che sia stato inserito un importo valido
            if (NumberUtils.isCreatable(importo)) {
                //valorizzo l'importo creando la concatenazione
                String importoConcatenato = tipologia + importo;
                return Double.parseDouble(importoConcatenato);
            } else
                //se l'importo inserito non è un numero valido allora lo setto a zero
                return 0.0;
        }
        //caso in cui tipologia è none allora mi interessa l'estratto conto
        return null;

    }
}
