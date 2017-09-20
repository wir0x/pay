package ch.swissbytes.ewallet.tigomoney.util;


import ch.swissbytes.ewallet.tigomoney.dto.TMPaymentRequestDto;
import ch.swissbytes.ewallet.util.TMStringUtils;

public class TMPaymentParamsUtils {

    public static String buildClientName(String name){
        return TMStringUtils.subStringNormalizeAccents(name, TMPaymentParamsLength.NOMBRE.getLength());
    }

    public static String buildOrderId(String orderId){
        return TMStringUtils.subString(orderId, TMPaymentParamsLength.ORDER_ID.getLength());
    }

    public static Integer buildLineNumber(Integer lineNumber){
        return Integer.parseInt(TMStringUtils.subString(lineNumber + "", TMPaymentParamsLength.LINEA.getLength()));
    }

    public static String buildDocumentNumber(String documentNumber){
        return TMStringUtils.subString(documentNumber, TMPaymentParamsLength.NRO_DOCUMENTO.getLength());
    }

    public static String buildCorrectUrl(String correctUrl){
        return TMStringUtils.subString(correctUrl, TMPaymentParamsLength.URL_CORRECTO.getLength());
    }

    public static String buildErrorUrl(String errorUrl){
        return TMStringUtils.subString(errorUrl, TMPaymentParamsLength.URL_ERROR.getLength());
    }

    public static String buildNotificationMessage(String notificationMessage){
        return TMStringUtils.subString(notificationMessage, TMPaymentParamsLength.NOTIFICACION.getLength());
    }

    public static String buildConfirmationMessage(String confirmationMessage){
        return TMStringUtils.subString(confirmationMessage, TMPaymentParamsLength.CONFIRMACION.getLength());
    }

    public static String buildTMMessage(String confirmationMessage){
        return TMStringUtils.subString(confirmationMessage, TMPaymentParamsLength.MENSAJE.getLength());
    }

    public static String buildRazonSocial(String razonSocial){
        return TMStringUtils.subString(razonSocial, TMPaymentParamsLength.RAZON_SOCIAL.getLength());
    }

    public static String buildNit(String razonSocial){
        return TMStringUtils.subString(razonSocial, TMPaymentParamsLength.NIT.getLength());
    }

    public static TMPaymentRequestDto analyze(TMPaymentRequestDto payment){
        if(payment!=null){

            payment.setName(buildClientName(payment.getName()));
            payment.setDocumentNumber(buildDocumentNumber(payment.getDocumentNumber()));

            payment.setOrderId(buildOrderId(payment.getOrderId()));
            payment.setLine(buildLineNumber(payment.getLine()));

            payment.setBusinessName(buildRazonSocial(payment.getBusinessName()));
            payment.setNit(buildNit(payment.getNit()));

            payment.setConfirmation(buildConfirmationMessage(payment.getConfirmation()));
            payment.setMessage(buildTMMessage(payment.getMessage()));
            payment.setNotification(buildNotificationMessage(payment.getNotification()));

            payment.setCorrectUrl(buildCorrectUrl(payment.getCorrectUrl()));
            payment.setErrorUrl(buildErrorUrl(payment.getErrorUrl()));
        }
        return payment;
    }
}
