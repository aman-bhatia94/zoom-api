package utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String trimByOne(String str) {
        if (str.length() < 2)
            return str;
        return str.substring(1, str.length() - 1);
    }

    boolean requireKeys(Map<String, String> d, String[] keys, boolean allowNone) {
//        """Require that the object have the given keys
//
//    :param d: The dict the check
//    :param keys: The keys to check :attr:`obj` for. This can either be a single
//                 string, or an iterable of strings
//
//    :param allow_none: Whether ``None`` values are allowed
//    :raises:
//        :ValueError: If any of the keys are missing from the obj

        try {
            for (String k : keys) {
                if (!d.containsKey(k)) {
                    throw new Exception(String.format("%s must be set", k));
                }
                if (!allowNone && d.get(k) == null) {
                    throw new Exception(String.format("%s cannot be None", k));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return true;
    }

    String dateToString(Date d) {
//        """Convert date and datetime objects to a string
//
//    Note, this does not do any timezone conversion.
//
//    :param d: The :class:`datetime.date` or :class:`datetime.datetime` to
//              convert to a string
//    :returns: The string representation of the date
//    """
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
}
