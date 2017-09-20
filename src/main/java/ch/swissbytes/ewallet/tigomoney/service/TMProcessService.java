package ch.swissbytes.ewallet.tigomoney.service;

import ch.swissbytes.ewallet.tigomoney.dto.TMConnectionDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentRequestDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentResponseDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMStatusResponseDto;
import ch.swissbytes.ewallet.tigomoney.util.TripleDes;
import ch.swissbytes.ewallet.tigomoney.wsimport.customerservices.CustomerServices;
import ch.swissbytes.ewallet.tigomoney.wsimport.customerservices.CustomerServices_Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

public class TMProcessService implements Serializable {

    private final static Logger log = LogManager.getLogger(TMProcessService.class);
    private final QName qName = new QName("http://services.vlink.com.bo/", "CustomerServices");
    private TMConnectionDto tmConnectionDto;
    private CustomerServices customerServices;

    public TMProcessService(final TMConnectionDto tmConnectionDto) {
        this.tmConnectionDto = tmConnectionDto;
        customerServices = new CustomerServices_Service(tmConnectionDto.getUrl(), qName).getCustomerServicesPort();
    }

    public TMPaymentResponseDto requestPayment(final TMPaymentRequestDto payment) throws Exception {
        log.warn("payment: {}", payment);
        TripleDes tDes = TripleDes.createNew(tmConnectionDto.getEncryptionKey());
        String result = customerServices.solicitarPago(tmConnectionDto.getIdKey(), tDes.encrypt(payment.toString()));
        String decryptedResult = tDes.decrypt(result);
        return TMPaymentResponseDto.createNew(decryptedResult);
    }

    public TMStatusResponseDto checkStatus(final String orderId) throws Exception {
        log.info("orderId: {}", orderId);
        TripleDes tDes = TripleDes.createNew(tmConnectionDto.getEncryptionKey());
        String result = customerServices.consultarEstado(tmConnectionDto.getIdKey(), tDes.encrypt(orderId));
        String decryptedResult = tDes.decrypt(result);
        return TMStatusResponseDto.createNew(decryptedResult);
    }

    public boolean hasConnection() {
        URL url = tmConnectionDto.getUrl();
        log.info("url: {}", url);
        boolean isConnected = false;
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.getInputStream();
            isConnected = true;
        } catch (IOException e) {
            log.error("can not connect to url: " + url, e);
        } finally {
            return isConnected;
        }
    }
}
