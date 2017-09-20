package ch.swissbytes.ewallet.tigomoney.util;

/**
 * Created by eliana on 10/11/2015.
 */
public enum TMPaymentParamsLength {

    NRO_DOCUMENTO(20),
    ORDER_ID(20),
    MENSAJE(100),
    LINEA(8),
    NOMBRE(100),
    URL_CORRECTO(200),
    URL_ERROR(200),
    CONFIRMACION(20),
    NOTIFICACION(20),
    RAZON_SOCIAL(40),
    NIT(20);

    private int length;

    private TMPaymentParamsLength(int length){
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
