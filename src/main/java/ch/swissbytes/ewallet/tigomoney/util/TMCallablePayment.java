package ch.swissbytes.ewallet.tigomoney.util;

import ch.swissbytes.ewallet.tigomoney.dto.TMConnectionDto;
import ch.swissbytes.ewallet.tigomoney.service.TMProcessService;
import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentRequestDto;
import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

public class TMCallablePayment implements Callable<TMPaymentResponseDto> {

    private final static Logger log = LogManager.getLogger(TMCallablePayment.class);
    private TMProcessService tmProcessService;
    private TMPaymentRequestDto paymentDto;

    public TMCallablePayment(TMConnectionDto tmConnectionDto, TMPaymentRequestDto paymentDto) {
        this.tmProcessService = new TMProcessService(tmConnectionDto);
        this.paymentDto = paymentDto;
    }

    @Override
    public TMPaymentResponseDto call() throws Exception {
        log.warn("payment: {}", this.paymentDto);
        return this.tmProcessService.requestPayment(this.paymentDto);
    }
}
