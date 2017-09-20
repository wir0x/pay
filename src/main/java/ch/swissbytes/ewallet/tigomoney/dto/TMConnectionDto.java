package ch.swissbytes.ewallet.tigomoney.dto;

import ch.swissbytes.ewallet.tigomoney.util.TMResourceProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
@ToString
/**
 * @param url
 * @param encryptionKey
 * @param idKey
 */
public class TMConnectionDto implements Serializable {

    private final static Logger log = LogManager.getLogger(TMConnectionDto.class);
    private String url;
    private String encryptionKey;
    private String idKey;

    public TMConnectionDto() {
    }

    public TMConnectionDto(String url, String encryptionKey, String idKey) {
        this.url = url;
        this.encryptionKey = encryptionKey;
        this.idKey = idKey;
    }

    public static TMConnectionDto createNew() {
        //set testing connection by default
        TMConnectionDto tmConnectionDto = new TMConnectionDto();
        tmConnectionDto.url = TMResourceProvider.getTigoMoneyUrl();
        tmConnectionDto.encryptionKey = TMResourceProvider.getTigoMoneyEncryptionKey();
        tmConnectionDto.idKey = TMResourceProvider.getTigoMoneyIdKey();
        return tmConnectionDto;
    }

    public URL getUrl() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            log.error("url malformed: " + this.url);
            return null;
        }
    }
}
