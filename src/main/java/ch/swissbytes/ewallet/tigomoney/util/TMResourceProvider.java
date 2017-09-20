package ch.swissbytes.ewallet.tigomoney.util;

import ch.swissbytes.ewallet.util.TMEntityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TMResourceProvider {

    private final static Logger log = LogManager.getLogger(TMResourceProvider.class);

    private static final String RESOURCE_BUNDLE_NAME = "config";

    private static final String TM_URL = "tm.url";
    private static final String TM_ENCRYPTION_KEY = "tm.encryption.key";
    private static final String TM_ID_KEY = "tm.id.key";
    private static final String TM_PAYMENT_REQUEST_TIMEOUT = "tm.payment.request.timeout";

    private static String get(String key) {
        String value = "";
        try {
            value = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME).getString(key);
        } catch (MissingResourceException ex) {
            log.error("key not found [" + key + "]");
        }
        return value;
    }

    public static String getTigoMoneyUrl() {
        return get(TM_URL);
    }

    public static String getTigoMoneyEncryptionKey() {
        return get(TM_ENCRYPTION_KEY);
    }

    public static String getTigoMoneyIdKey() {
        return get(TM_ID_KEY);
    }

    public static int getPaymentRequestTimeout() {
        int value = TMEntityUtil.DEFAULT_INTEGER;
        try {
            value = Integer.valueOf(get(TM_PAYMENT_REQUEST_TIMEOUT));
        } catch (NumberFormatException ex) {
            value = 3;
        } finally {
            return value;
        }
    }
}
