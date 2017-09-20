package ch.swissbytes.ewallet.service;

import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentRequestDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentResponseDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMStatusResponseDto;

public interface EWalletService {

    /**
     * @param payment
     * @return The response processed by Tigo Money error code and description
     * If it is success code = 0 for else fail
     */
    TMPaymentResponseDto requestPayment(TMPaymentRequestDto payment);


    /**
     * @param payment
     * @return The response processed by Tigo Money error code and description
     * If it is success code = 0 for else fail
     */
    TMPaymentResponseDto requestPayment(TMPaymentRequestDto payment, int timeoutInMinutes);


    /**
     * @param orderId
     * @return 0 = success or 1 = fail.
     * If the orderId contains more than one request and someone is error it will return 1 (fail).
     */
    TMStatusResponseDto checkStatus(String orderId);

    /**
     * @return true if the Tigo Money webservice is online
     */
    boolean hasConnection();

}
