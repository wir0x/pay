package ch.swissbytes.ewallet.tigomoney.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class TripleDes {
    private final static Logger log = LogManager.getLogger(TripleDes.class);
    private static final String UNICODE_FORMAT = "UTF-8";
    private static final String ENCRYPTION_SCHEME = "DESede/ECB/ZeroBytePadding";
    private static final String ALGORITHM_SECRET = "DESede";

    private byte[] privateKey;

    private TripleDes(String newEncryptionKey) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        privateKey = newEncryptionKey.getBytes(UNICODE_FORMAT);
    }

    public static TripleDes createNew(String newEncryptionKey) throws Exception {
        return new TripleDes(newEncryptionKey);
    }

    public String encrypt(final String message) throws Exception {
        log.info("message: " + message);
        final SecretKey key = new SecretKeySpec(privateKey, ALGORITHM_SECRET);
        final Cipher cipher = Cipher.getInstance(ENCRYPTION_SCHEME, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        final byte[] plainTextBytes = message.getBytes(UNICODE_FORMAT);
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        Base64 encoder = new Base64();
        String encryptedMessage = new String(encoder.encode(cipherText));
        log.info("encrypted message: " + encryptedMessage);
        return encryptedMessage;
    }

    public String decrypt(String message) throws Exception {
        log.info("message: " + message);
        if (message == null)
            throw new Exception("null response");
        final SecretKey key = new SecretKeySpec(privateKey, ALGORITHM_SECRET);
        final Cipher decipher = Cipher.getInstance(ENCRYPTION_SCHEME, "BC");
        decipher.init(Cipher.DECRYPT_MODE, key);
        message = message.replace(" ", "+");
        Base64 encoder = new Base64();
        final byte[] plainText = decipher.doFinal(encoder.decode(message));
        String decryptedMessage = new String(plainText, UNICODE_FORMAT);
        log.info("decrypted message: "+ decryptedMessage);
        return decryptedMessage;
    }
}
