package ch.swissbytes.ewallet.tigomoney.util;

import ch.swissbytes.ewallet.util.TMEntityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TMPaymentResponseMsg {

    private final static Logger log = LogManager.getLogger(TMPaymentResponseMsg.class);
    private static final Locale locale = new Locale("es", "ES");
    private static final String RESOURCE_BUNDLE_NAME = "TMPaymentMessages";

    public static String get(Integer key) {
        return get(String.valueOf(key));
    }

    public static String get(String key) {
        try {
            return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale).getString(key);
        } catch (MissingResourceException ex) {
            log.error("Tigo Money ERROR CODE not found [{}]", key);
            return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale).getString(String.valueOf(TMEntityUtil.DEFAULT_ERROR_CODE));
        }
    }


}
