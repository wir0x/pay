package ch.swissbytes.ewallet.tigomoney.action;

import ch.swissbytes.ewallet.service.EWalletService;
import ch.swissbytes.ewallet.tigomoney.dto.TMConnectionDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentRequestDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentResponseDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMStatusResponseDto;
import ch.swissbytes.ewallet.tigomoney.service.TMProcessService;
import ch.swissbytes.ewallet.tigomoney.util.TMCallablePayment;
import ch.swissbytes.ewallet.tigomoney.util.TMPaymentParamsUtils;
import ch.swissbytes.ewallet.tigomoney.util.TMPaymentResponseMsg;
import ch.swissbytes.ewallet.tigomoney.util.TMResourceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.concurrent.*;

public class TigoMoneyServiceImpl implements EWalletService, Serializable {

    private final static Logger log = LogManager.getLogger(TigoMoneyServiceImpl.class);
    private final static int TIMEOUT_EXCEPTION_CODE = 1000001;
    private final static int INTERRUPTED_EXCEPTION_CODE = 1000002;
    private final static int EXCEPTION_CODE = 1001000;
    private TMConnectionDto tmConnectionDto;
    private TMProcessService TMProcessService;

    /**
     *
     * @param tmConnectionDto
     * with url, encryptionKey and idKey
     */
    public TigoMoneyServiceImpl(final TMConnectionDto tmConnectionDto) {
        this.tmConnectionDto = tmConnectionDto;
        this.TMProcessService = new TMProcessService(this.tmConnectionDto);
    }

    /**
     *
     * @param payment
     * @return TMPaymentResponseDto
     */
    public TMPaymentResponseDto requestPayment(final TMPaymentRequestDto payment) {
        return requestPayment(payment, TMResourceProvider.getPaymentRequestTimeout());
    }

    public TMPaymentResponseDto requestPayment(TMPaymentRequestDto payment, int timeoutInMinutes) {
        log.warn("payment before to analyze: {}", payment);

        payment = TMPaymentParamsUtils.analyze(payment);

        log.warn("payment before to after: {}", payment);

        log.warn("\n\n\n");
        log.warn("TIGO MONEY - Request Payment timeoutInMinutes= "+timeoutInMinutes);
        log.warn("payment: {}", payment);
        long timeSpend = System.currentTimeMillis();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        TMCallablePayment tmCallablePayment = new TMCallablePayment(this.tmConnectionDto, payment);
        Future<TMPaymentResponseDto> future = executor.submit(tmCallablePayment);
        TMPaymentResponseDto paymentResponse = TMPaymentResponseDto.createNew();

        try {
            paymentResponse = future.get(timeoutInMinutes, TimeUnit.MINUTES);
        } catch (TimeoutException e) {
            future.cancel(true);
            handlePaymentException("TimeoutException: ", e, paymentResponse, TIMEOUT_EXCEPTION_CODE);
        } catch (InterruptedException e) {
            handlePaymentException("InterruptedException: ", e, paymentResponse, INTERRUPTED_EXCEPTION_CODE);
        } catch (Exception e) {
            handlePaymentException("Exception: ", e, paymentResponse, EXCEPTION_CODE);
        } finally {
            executor.shutdownNow();
            timeSpend = System.currentTimeMillis() - timeSpend;
            log.warn("paymentResponse: " + paymentResponse);
            log.warn("time spend [" + timeSpend + " ms.]");
            return paymentResponse;
        }
    }


    /**
     *
     * @param orderId
     * @return TMStatusResponseDto
     */
    public TMStatusResponseDto checkStatus(final String orderId) {
        long timeSpend = System.currentTimeMillis();
        log.warn("\n\n\n");
        log.warn("TIGO MONEY - Check Status");
        log.warn("orderId: {}", orderId);
        TMStatusResponseDto statusResponse = TMStatusResponseDto.createNew();
        try {
            statusResponse = TMProcessService.checkStatus(orderId);
            return statusResponse;
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage(), e);
            statusResponse = TMStatusResponseDto.createNew();
        } finally {
            timeSpend = System.currentTimeMillis() - timeSpend;
            log.warn("statusResponse: " + statusResponse);
            log.warn("time spend [" + timeSpend + " ms.]");
            return statusResponse;
        }
    }

    /**
     *
     * @return if there is a connection to tigo money
     */
    public boolean hasConnection() {
        long timeSpend = System.currentTimeMillis();
        log.warn("\n\n\n");
        log.warn("TIGO MONEY - Has Connection");
        boolean hasConnection = false;
        try {
            hasConnection = TMProcessService.hasConnection();
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage(), e);
        } finally {
            timeSpend = System.currentTimeMillis() - timeSpend;
            log.warn("hasConnection: " + hasConnection);
            log.warn("time spend [" + timeSpend + " ms.]");
            return hasConnection;
        }
    }

    private void handlePaymentException(String msg, Exception e, TMPaymentResponseDto responseDto, Integer code) {
        log.error(msg + e.getMessage(), e);
        responseDto.setCode(code);
        responseDto.setDescription(TMPaymentResponseMsg.get(code));
    }
}
