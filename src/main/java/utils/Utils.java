package utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Utils {

    public static void requireKeys(Map<String, String> d, String[] keys, boolean allowNull) throws Exception {
        //Require that the object have the given keys
        //param d: The dict the check
        //param keys: The keys to check :attr:`obj` for. This can either be a single
        //            string, or an iterable of strings
        //param allowNull: Whether ``Null`` values are allowed
        for (String k : keys) {
            if (!d.containsKey(k)) {
                throw new Exception(String.format("%s must be set", k));
            }
            if (!allowNull && d.get(k) == null) {
                throw new Exception(String.format("%s cannot be Null", k));
            }
        }
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
        return d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static LocalDate stringToLocaleDate(String d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(d, formatter);
    }

    public static String getErrorMessageFromResponse(Map responseMap) {
        if (responseMap.containsKey("code") && responseMap.containsKey("message")) {
            return responseMap.get("code") + ": " + responseMap.get("message");
        } else
            return "Operation Failed";
    }
}
