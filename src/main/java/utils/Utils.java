package utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class Utils {

    public static boolean requireKeys(Map<String, String> d, String[] keys, boolean allowNull) {
        //Require that the object have the given keys
        //param d: The dict the check
        //param keys: The keys to check :attr:`obj` for. This can either be a single
        //            string, or an iterable of strings
        //param allowNull: Whether ``Null`` values are allowed
        //returns: Returns false if any of the keys are missing from the obj else true
        try {
            for (String k : keys) {
                if (!d.containsKey(k)) {
                    throw new Exception(String.format("%s must be set", k));
                }
                if (!allowNull && d.get(k) == null) {
                    throw new Exception(String.format("%s cannot be None", k));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return true;
    }

    public static String dateToString(Date d) {
        // Convert date and datetime objects to a string
        // Note, this does not do any timezone conversion.
        // param d: The :class:`datetime.date` or :class:`datetime.datetime` to convert to a string
        // returns: The string representation of the date
        DateFormat dateFormat = new SimpleDateFormat("%Y-%m-%dT%H:%M:%SZ");
        return dateFormat.format(d);
    }

    public static String appendToUrl(String url, Map<String, String> parameters) throws URISyntaxException {
        URI uri = new URI(url);
        String query = uri.getQuery();

        StringBuilder builder = new StringBuilder();

        if (query != null)
            builder.append(query);

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String keyValueParam = entry.getKey() + "=" + entry.getValue();
            if (!builder.toString().isEmpty())
                builder.append("&");

            builder.append(keyValueParam);
        }

        URI newUri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), builder.toString(), uri.getFragment());
        return newUri.toString();
    }

    public static String dateToString(LocalDate d) {


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.format(d);
        String str = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return str;
    }

    public static LocalDate stringToLocaleDate(String d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(d, formatter);
        return date;

//        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
